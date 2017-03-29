package com.demo.app.repository.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.configuration.security.User;

@Repository
public interface UserRepository extends CrudRepository<String, User>, UserRepositoryCustom {

}
