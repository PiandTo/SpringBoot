package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepositoryImpl extends JpaRepository<Film, Long> {
}
