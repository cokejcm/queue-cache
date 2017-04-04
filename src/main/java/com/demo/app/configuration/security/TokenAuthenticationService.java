package com.demo.app.configuration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.demo.app.util.Constants;

@Component
public class TokenAuthenticationService {

	private final TokenHandler tokenHandler;

	public TokenAuthenticationService(String secret, UserService userService) {
		tokenHandler = new TokenHandler(secret, userService);
	}

	public String addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final User user = authentication.getDetails();
		String token = tokenHandler.createTokenForUser(user);
		response.addHeader(Constants.AUTH_HEADER_NAME, token);
		return token;
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(Constants.AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				user.eraseCredentials(); //No need to hold this. Security reasons.
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
