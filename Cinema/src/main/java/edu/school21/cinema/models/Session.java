package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table (name = "session", schema = "springboot")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @JsonIgnore
    @NotNull(message = "You can't leave this field empty")
    @ManyToOne(fetch=FetchType.EAGER)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @JsonIgnore
    @NotNull(message = "You can't leave this field empty")
    @ManyToOne(fetch=FetchType.EAGER)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @NotNull(message = "You can't leave this field empty")
    @Column(name = "ticket_cost")
    private BigDecimal ticketCost;
}
