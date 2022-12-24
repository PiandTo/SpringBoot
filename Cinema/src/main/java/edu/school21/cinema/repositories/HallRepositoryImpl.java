package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepositoryImpl extends JpaRepository<Hall, Long> {
}
