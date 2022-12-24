package edu.school21.cinema.services;

import edu.school21.cinema.models.EmailVerification;
import edu.school21.cinema.repositories.EmailVerificationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
	@Autowired
	EmailVerificationRepositoryImpl emailVerificationRepository;

	public String generateVerification(String username) {
		if (!emailVerificationRepository.existsByUsername(username)) {
			EmailVerification emailVerification = new EmailVerification();
			emailVerification.setUsername(username);
			emailVerificationRepository.save(emailVerification);
			return emailVerification.getVerificationId();
		}
		return getVerificationIdByUsername(username);
	}

	public String getVerificationIdByUsername(String username) {
		EmailVerification emailVerification = emailVerificationRepository.findEmailVerificationByUsername(username);
		if (emailVerification == null) {
			return null;
		}
		return emailVerification.getVerificationId();
	}

	public String getUsernameByVerificationId(String id) {
		EmailVerification emailVerification = emailVerificationRepository.findEmailVerificationByVerificationId(id);
		if (emailVerification == null) {
			return null;
		}
		return emailVerification.getUsername();
	}
}
