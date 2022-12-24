package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepositoryImpl extends JpaRepository<Message, Long> {
	List<Message> findAllByFilmId(Long id);
	void deleteAllByFilm_Id(Long id);
}
