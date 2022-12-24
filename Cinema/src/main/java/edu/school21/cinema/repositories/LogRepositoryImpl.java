package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepositoryImpl extends JpaRepository<Logs, Long> {
	List<Logs> findAllByEmail(String email);
}
