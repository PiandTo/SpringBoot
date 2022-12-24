package edu.school21.cinema.security;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.services.EmailVerificationService;
import edu.school21.cinema.services.LoginAttemptService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	private final LoginAttemptService loginAttemptService;
	private static Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = userRepository.findUserByEmail(username);

		if (opt.isPresent()) {
			User user = opt.get();
			if (loginAttemptService.isBlocked(username)) {
				logger.info(username + " is blocked because more than 3 incorrect credentials");
			}
			return new SecurityUser(user);
		} else {
			throw new UsernameNotFoundException("User with name " + username + " not find :(");
		}
	}
}
