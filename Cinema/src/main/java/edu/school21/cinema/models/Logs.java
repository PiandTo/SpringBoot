package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "logs", schema = "springboot")
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
	@Id
	@GeneratedValue
	@Column(name="log_id")
	private Long id;

	@Column(name="email")
	private String email;

	@Column(name="ip")
	private String ip;

	@Column(name="time")
	private String time;

	@Column(name="date")
	private String date;
}
