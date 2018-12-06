package com.microservice.model;

public class User {
	private String username;
	private String passowrd;
	private String name;
	private Role role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String passowrd, String name, Role role) {
		super();
		this.username = username;
		this.passowrd = passowrd;
		this.name = name;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", passowrd=" + passowrd + ", name=" + name + ", role=" + role + "]";
	}
}