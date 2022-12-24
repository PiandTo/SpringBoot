package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;

import java.util.List;

public interface HallService {
    List<Hall> findAll();
    void update(Hall obj);
    Hall findById(Long id);
    void save(Hall obj);
    void delete(Hall obj);
}
