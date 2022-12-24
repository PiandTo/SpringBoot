package edu.school21.cinema.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "messages", schema = "springboot")
public class Message {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="content")
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @Column(name = "type")
    public MessageType type;

    public enum MessageType {
        JOIN,
        LEAVE,
        CHAT
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
