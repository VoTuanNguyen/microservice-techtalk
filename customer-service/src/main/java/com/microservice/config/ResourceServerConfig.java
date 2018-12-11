package com.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/welcome/**").permitAll()
			.antMatchers("/getinfo/**").permitAll()
			.antMatchers("/actuator/**").permitAll()
			.antMatchers("/**").permitAll()
			.antMatchers("/profile/**").access("hasAnyRole('ADMIN', 'USER')")
			.antMatchers("/viewall/**").access("hasRole('ADMIN')")
			.antMatchers("/adduser/**").access("hasRole('ADMIN')")
			.anyRequest().authenticated();
	}
} 