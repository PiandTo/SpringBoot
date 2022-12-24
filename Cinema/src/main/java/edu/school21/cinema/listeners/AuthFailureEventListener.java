package edu.school21.cinema.listeners;

import edu.school21.cinema.services.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Service;

@Service
public class AuthFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private static final Logger logger = LoggerFactory.getLogger(AuthFailureEventListener.class);

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		String username = (String)event.getAuthentication().getPrincipal();
		loginAttemptService.loginFailed(username);
		logger.info("AuthFailureEventListener: " + username);
	}
}
