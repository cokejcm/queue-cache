package com.demo.app.configuration.security;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity(name="security.users")
public class User implements Serializable{

	private static final long serialVersionUID = -7367637520434896223L;

	private String username;
	private String password;
	private boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}