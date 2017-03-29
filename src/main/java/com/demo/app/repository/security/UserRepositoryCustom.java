package com.demo.app.repository.security;

import org.springframework.data.jpa.repository.Query;

import com.demo.app.configuration.security.User;

public interface UserRepositoryCustom {

	@Query("select enabled from User u where u.username = :#{#user.username}")
	public boolean validateUser(User user);
}
