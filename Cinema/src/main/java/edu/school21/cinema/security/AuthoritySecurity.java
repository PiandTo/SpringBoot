package edu.school21.cinema.security;

import edu.school21.cinema.models.Authorities;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AuthoritySecurity implements GrantedAuthority {
	private final Authorities authorities;

	@Override
	public String getAuthority() {
		return authorities.getName();
	}
}
