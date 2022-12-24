package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name="hall", schema="springboot")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long hallId;

    @NotNull (message = "You can't leave this field empty")
    @Column(name = "name")
    @Size(min=2, max=30, message = "Name should be longer than 2 character and not more 30 chars")
    private String name;

    @NotNull (message = "You can't leave this field empty")
    @Column (name = "number")
    @Min(value = 1, message = "Field should be not less than 1")
    private Integer number;

    @NotNull (message = "You can't leave this field empty")
    @Column (name = "availableseats")
    @Min(value = 1, message = "Field should be not less than 1")
    private Integer availableSeats;

    @ToString.Exclude
    @OneToMany(mappedBy = "hall", fetch = FetchType.EAGER)
    private List<Session> sessionHalls;

    public void addSessionToHall(Session session) {
        if (sessionHalls == null)
            sessionHalls = new ArrayList<>();
        sessionHalls.add(session);
        session.setHall(this);
    }
}
