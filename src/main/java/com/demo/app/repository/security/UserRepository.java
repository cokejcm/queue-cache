package com.demo.app.repository.security;

import org.springframework.data.repository.CrudRepository;

import com.demo.app.configuration.security.User;

public interface UserRepository extends CrudRepository<String, User> {

}
