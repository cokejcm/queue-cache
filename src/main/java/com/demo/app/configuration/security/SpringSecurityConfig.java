package com.demo.app.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final TokenAuthenticationService tokenAuthenticationService;

	public SpringSecurityConfig() {
		super(true);
		this.userService = new UserService();
		tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

				.anonymous().and().authorizeRequests().antMatchers("/login").permitAll()
				.anyRequest().authenticated();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure auth");
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return tokenAuthenticationService;
	}

}
