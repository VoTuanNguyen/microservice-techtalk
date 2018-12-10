package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.User;
import com.microservice.service.UserService;

@RestController
@RefreshScope
public class Controller {

	@Value("${config.variable}")
	private String welcome;
	@Autowired
	private UserService userService;

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.ok(welcome);
	}

	@GetMapping("/getinfo/{username}")
	public ResponseEntity<User> getInfoUser(@PathVariable String username) {
		return ResponseEntity.ok().body(userService.getUserByUsername(username));
	}

	@GetMapping("/profile")
	public ResponseEntity<User> getProfile() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@GetMapping("/viewall")
	public ResponseEntity<?> viewAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.addUser(user));
	}
}
