package edu.school21.cinema.models;

import edu.school21.cinema.validation.ValidEmail;
import edu.school21.cinema.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="users", schema = "springboot")
public class User {
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private long id;

	@Column(name="first_name")
	@NotEmpty(message = "{errors.incorrect.firstName}")
	private String firstName;

	@NotEmpty(message = "{errors.incorrect.lastName}")
	@Column(name="last_name")
	private String lastName;

	@NotEmpty(message = "{errors.incorrect.email}")
	@Email
	@ValidEmail(message = "{errors.double.email}")
	@Column(name="email")
	private String email;

	@Pattern(regexp="(^$|\\+\\d\\(\\d{3}\\)\\d{7})", message="{errors.incorrect.phone}")
	@NotEmpty(message = "{errors.incorrect.phone}")
	@Column(name="phone_number")
	private String phoneNumber;

	@ValidPassword(message = "{errors.incorrect.password}")
	@Column(name="password")
	private String password;

	@ManyToMany
	private Set<Role> role;

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private ProfileStatus profileStatus;

	@Column(name="avatar")
	private String avatar;

	@Column(name="is_non_locked")
	private boolean isNonLocked = true;

	@Column(name="is_non_enable")
	private boolean isNonEnable = false;

	@Column(name="fail_attempts")
	private int failAttempts = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="springboot.users_authorities",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="authorities_id"))
    private Set<Authorities> authorities;
}
