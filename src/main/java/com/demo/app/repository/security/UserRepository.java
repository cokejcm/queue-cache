package com.demo.app.repository.security;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.configuration.security.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("select enabled from User u where u.username = :#{#user.username}")
	public boolean validateUser(User user);

}
