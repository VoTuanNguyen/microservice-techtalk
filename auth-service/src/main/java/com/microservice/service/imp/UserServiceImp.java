package com.microservice.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entity.User;
import com.microservice.repository.UserRepository;
import com.microservice.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

}
