package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public void update(User user) {
		userRepository.save(user);
	}
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		Optional<User> opt = userRepository.findUserByEmail(email);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public User findById(Long id) {
		User user = null;
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			user = optional.get();
		}
		return user;
	}
}
