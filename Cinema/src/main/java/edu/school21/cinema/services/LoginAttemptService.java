package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginAttemptService {
	@Autowired
	private UserService userService;

	private static final int MAX_ATTEMPTS_COUNT = 3;

	public void loginSuccess(String username) {
		User user = userService.findUserByEmail(username);
		if (user != null) {
			user.setFailAttempts(0);
			userService.update(user);
		}
	}

//	Increments the failed login attempt counter for the specified username
	public void loginFailed(String username) {
		User user = userService.findUserByEmail(username);
		int failedAttemptCounter = user.getFailAttempts();
		failedAttemptCounter++;
		user.setFailAttempts(failedAttemptCounter);
		if (failedAttemptCounter >= MAX_ATTEMPTS_COUNT) {
			user.setNonLocked(false);
		}
		userService.update(user);
	}

//	Indicates whether the user has exceeded the maximum number of allowed login attempts
	public boolean isBlocked(String username) {
		User user = userService.findUserByEmail(username);
		if (user != null && user.getFailAttempts() >= MAX_ATTEMPTS_COUNT) {
			return true;
		} else {
			return false;
		}
	}
}
