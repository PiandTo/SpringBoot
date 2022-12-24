package edu.school21.cinema.models;
import lombok.Data;
import java.time.format.DateTimeFormatter;

@Data
public class SessionSearch {
    private Long id;
    private String dateTime;
    private FilmSearch film;

    public SessionSearch(Session session) {
        this.id = session.getId();
        this.dateTime = session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        this.film = new FilmSearch(session);
        }
    }