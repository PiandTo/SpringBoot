package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "film", schema = "springboot")
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "title")
    @NotNull
    @Size(min=2, max=50, message = "Field should be more 2 chars and less 50 chars")
    private String title;

    @Column (name = "year")
    @NotNull
    @Size(min=3, max=5, message = "Field should be 4 chars")
    private String yearRelease;

    @Column (name = "age")
    @NotNull
    private int ageRestriction;

    @NotNull
    @Size(min=2, max=50, message = "Field should be more 2 chars and less 50 chars")
    @Column (name = "description")
    private String description;

    @Column (name = "poster")
    private String poster;

    @ToString.Exclude
    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private List<Session> sessionFilms;
}
