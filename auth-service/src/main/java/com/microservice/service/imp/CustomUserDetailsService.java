package com.microservice.service.imp;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println(this.passwordEncoder.encode("user"));
//		System.out.println(this.passwordEncoder.encode("admin"));
		com.microservice.entity.User user = userService.getUserByUsername(username);
		UserDetails userDetails = User.withUsername(user.getUsername())
				.password(user.getPassword()).roles(user.getRole().getRole()).build();

		return Stream.of(userDetails).filter(u -> u.getUsername().equals(username)).findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("Couldn't find the username " + username + "!"));
	}
}