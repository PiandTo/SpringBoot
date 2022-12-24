package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepositoryImpl extends JpaRepository<Session, Long> {
    List<Session> findByFilm_Title(String filmName);
    List<Session> findAll();
    @Query("select s from Session s where s.id = ?1")
    Optional<Session> findById(Long id);

}
