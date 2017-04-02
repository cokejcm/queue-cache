package com.demo.app.controller;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.security.TokenAuthenticationService;
import com.demo.app.configuration.security.UserAuthentication;
import com.demo.app.configuration.security.UserService;

@Path("/")
@Component
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public void authenticate(@Context HttpServletResponse response, String username, String password) {
		// Validate the credentials against the Db
		User user;
		try {
			user = userService.loadUserByUsername(username);
			if (passwordEncoder.matches(password, user.getPassword())) {
				// Login that user
				UserAuthentication authentication = new UserAuthentication(user);
				tokenAuthenticationService.addAuthentication(response, authentication);
			} else {
				throw new WebApplicationException(Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity("").build());
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity("").build());
		}
	}

}
