package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface UserService {
	void update(User user);
	void save(User user);
	User findById(Long id);
	User findUserByEmail(String email);
}
