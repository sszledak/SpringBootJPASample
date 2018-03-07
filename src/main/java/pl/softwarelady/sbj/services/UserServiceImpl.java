package pl.softwarelady.sbj.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.softwarelady.sbj.jpa.UserModel;
import pl.softwarelady.sbj.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserModel> allUsers() {
		List<UserModel> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public UserModel getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public UserModel merge(UserModel user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
