package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import java.util.List;

public interface FilmService
{
    List<Film> findAll();
    void update(Film obj);
    Film findById(Long id);
    void save(Film obj);
    void delete(Film obj);
}
