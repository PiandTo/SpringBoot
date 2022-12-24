package edu.school21.cinema.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "authority", schema = "springboot")
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="authorities_id")
	private int authoritiesId;
	private String name;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> user;
}
