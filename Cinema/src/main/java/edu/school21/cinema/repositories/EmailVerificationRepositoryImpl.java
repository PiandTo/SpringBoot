package edu.school21.cinema.repositories;

import edu.school21.cinema.models.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepositoryImpl extends JpaRepository<EmailVerification, String> {
	EmailVerification findEmailVerificationByUsername(String username);
	EmailVerification findEmailVerificationByVerificationId(String id);
	boolean existsByUsername(String username);
}
