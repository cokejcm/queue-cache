package com.demo.app.dao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.security.Authority;
import com.demo.app.repository.security.AuthorizationRepository;

@Repository
public class AuthorityDao  {

	@Autowired
	AuthorizationRepository authorizationRepository;

	public Authority saveOne(Authority authority){
		return authorizationRepository.save(authority);
	}

	public void deleteOne(Authority authority){
		authorizationRepository.delete(authority);
	}


}
