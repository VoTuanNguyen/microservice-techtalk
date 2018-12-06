package com.microservice.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.microservice.model.User;

@FeignClient(name = "customer-service")
@RibbonClient(name = "customer-service")
public interface ServiceProxy {
	@GetMapping("/viewall")
	public ResponseEntity<?> viewAll(@RequestHeader("Authorization") String authorization);

	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestHeader("Authorization") String authorization, @RequestBody User user);
}
