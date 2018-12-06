package com.microservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Controller {
	@Value("${config.variable}")
	private String variable;

	@RequestMapping("/getvar")
	public String getVar() {
		return variable;
	}
}
