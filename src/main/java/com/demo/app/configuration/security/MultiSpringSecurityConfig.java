package com.demo.app.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MultiSpringSecurityConfig {

	@Configuration
	@Order(1)
	public static class LoginSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final UserService userService;
		private final TokenAuthenticationService tokenAuthenticationService;

		public LoginSecurityConfigurationAdapter() {
			super();
			this.userService = new UserService();
			tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.antMatchers("**/login").permitAll();

		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.userDetailsService(userDetailsService())
					.passwordEncoder(new BCryptPasswordEncoder());
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

	@Configuration
	@Order(2)
	public static class AuthenticateWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.anyRequest().authenticated();
		}
	}
}