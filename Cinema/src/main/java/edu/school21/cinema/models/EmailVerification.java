package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "email_verification", schema = "springboot")
public class EmailVerification {
	@Id
	@GeneratedValue(generator = "UUID_GENERATOR")
	@GenericGenerator(name="UUID_GENERATOR", strategy = "org.hibernate.id.UUIDGenerator")
	private String verificationId;
	private String username;
}
