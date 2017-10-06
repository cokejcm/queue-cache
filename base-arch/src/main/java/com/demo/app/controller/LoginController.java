package com.demo.app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.security.TokenAuthenticationService;
import com.demo.app.configuration.security.UserAuthentication;
import com.demo.app.service.security.UserService;
import com.demo.app.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/user")
@Component
@Api(tags = { "User" })
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Value("${domain}")
	private String domain;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	@ApiOperation(value = "Login for the users. Generates the JWT token.", notes = "Anyone can access. In the event of a succesful login it retrieves a token in the header")
	public void authenticate(@Context HttpServletResponse response, com.demo.app.domain.security.User userForm) {
		// Validate the credentials against the Db
		User user;
		try {
			user = userService.loadUserByUsername(userForm.getUsername());
			if (passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
				// Generate the token and send it back in the header
				UserAuthentication authentication = new UserAuthentication(user);
				String token = tokenAuthenticationService.addAuthentication(response, authentication);
				// Add Locale cookie
				Cookie cookie = new Cookie(Constants.COOKIE_LANGUAGE, userForm.getCountryCode());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 10); // 10 years
				cookie.setDomain(domain);
				cookie.setPath(Constants.CONTEXT);
				response.addCookie(cookie);
				// Add JWT cookie for stomp
				Cookie jwtCookie = new Cookie(Constants.AUTH_HEADER_NAME, token);
				jwtCookie.setPath(Constants.STOMP_URL);
				jwtCookie.setDomain(domain);
				cookie.setMaxAge(60 * 60 * 24 * 365); // 1 year
				// jwtCookie.setSecure(true);
				response.addCookie(jwtCookie);
			} else {
				throw new WebApplicationException(Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity("").build());
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity("").build());
		} finally {
			userForm = null;
			user = null;
		}
	}

}
