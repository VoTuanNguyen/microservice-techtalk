package com.microservice.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.entity.User;
import com.microservice.repository.UserRepository;
import com.microservice.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}

}
