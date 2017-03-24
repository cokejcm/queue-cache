package com.demo.app.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class CSRFConfig {

	@Autowired
	SecretService secretService;

	@Bean
	public CsrfTokenRepository jwtCsrfTokenRepository() {
		return new JWTCsrfTokenRepository(secretService.getHS512SecretBytes());
	}
}
