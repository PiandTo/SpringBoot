package edu.school21.cinema.services;

import edu.school21.cinema.models.Logs;
import edu.school21.cinema.repositories.LogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LogAuthService {
	@Autowired
	LogRepositoryImpl logRepository;

	@Transactional
	public void save(Logs log) {
		logRepository.save(log);
	}

	public List<Logs> findAllLogsByEmail(String email) {
		return logRepository.findAllByEmail(email);
	}
}
