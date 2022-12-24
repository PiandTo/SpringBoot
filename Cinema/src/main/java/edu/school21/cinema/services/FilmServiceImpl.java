package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepositoryImpl;
import edu.school21.cinema.repositories.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepositoryImpl crudRepository;

    @Autowired
    private MessageRepositoryImpl messageRepository;

    @Override
    public List<Film> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public void update(Film obj) {
        crudRepository.save(obj);
    }

    @Override
    public Film findById(Long id) {
        Optional<Film> tmp = crudRepository.findById(id);
        return tmp.orElse(null);
    }

    @Override
    @Transactional
    public void save(Film obj) {
        crudRepository.save(obj);
    }

    @Override
    public void delete(Film obj) {
        messageRepository.deleteAllByFilm_Id(obj.getId());
        crudRepository.delete(obj); }

}
