package com.microservice.service;

import java.util.List;

import com.microservice.entity.User;

public interface UserService {
	User getUserByUsername(String username);
	List<User> findAll();
	User addUser(User user);
}

