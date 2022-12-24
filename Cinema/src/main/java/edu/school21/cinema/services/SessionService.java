package edu.school21.cinema.services;
import edu.school21.cinema.models.Session;
import org.aspectj.apache.bcel.classfile.Module;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<Session> findAll();
    void update(Session obj);
    Optional<Session> findById(Long id);
    List<Session> findByFilmName(String filmName);
    void save(Session obj);
    void delete(Session obj);
}
