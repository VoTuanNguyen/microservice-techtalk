package com.microservice.service;

import com.microservice.entity.User;

public interface UserService {
	User getUserByUsername(String username);
}
