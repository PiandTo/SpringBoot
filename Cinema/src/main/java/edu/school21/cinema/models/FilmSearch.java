package edu.school21.cinema.models;
import lombok.Data;

@Data
public class FilmSearch {
    private String name;
    private String posterUrl;

    public FilmSearch(Session session) {
        this.name = session.getFilm().getTitle();
        this.posterUrl = session.getFilm().getPoster();
    }
}
