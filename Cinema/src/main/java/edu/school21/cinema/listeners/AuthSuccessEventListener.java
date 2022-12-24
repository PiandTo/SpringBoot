package edu.school21.cinema.listeners;

import edu.school21.cinema.security.SecurityUser;
import edu.school21.cinema.services.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

@Service
public class AuthSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
	private static final Logger logger = LoggerFactory.getLogger(AuthSuccessEventListener.class);

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		SecurityUser user = (SecurityUser) event.getAuthentication().getPrincipal();
		String username = user.getUsername();
		loginAttemptService.loginSuccess(username);
		logger.info("AuthSuccessEventListener: " + username);
	}
}
