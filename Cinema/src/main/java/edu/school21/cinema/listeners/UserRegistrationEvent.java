package edu.school21.cinema.listeners;

import edu.school21.cinema.models.User;
import org.springframework.context.ApplicationEvent;

public class UserRegistrationEvent extends ApplicationEvent {
	private User user;

	public UserRegistrationEvent(User user) {
		super(user);
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}
}
