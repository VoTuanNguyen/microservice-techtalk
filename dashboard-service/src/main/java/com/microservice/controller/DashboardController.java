package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.feign.ServiceProxy;
import com.microservice.model.User;

@RestController
@RefreshScope
public class DashboardController {
	@Autowired
	private ServiceProxy serviceProxy;
	
	@GetMapping("/viewall")
	public ResponseEntity<?> viewAll(@RequestHeader("Authorization") String authorization) {
		return serviceProxy.viewAll(authorization);
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestHeader("Authorization") String authorization, @RequestBody User user) {
		return serviceProxy.addUser(authorization, user);
	}
}
