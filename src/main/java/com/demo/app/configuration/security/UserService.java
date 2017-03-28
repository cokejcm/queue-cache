package com.demo.app.configuration.security;

import java.util.HashMap;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
	private final HashMap<String, User> userMap = new HashMap<>();

	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userMap.get(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		detailsChecker.check(user); //Check locked, enabled, expired
		return user;
	}

	public void addUser(User user) {
		userMap.put(user.getUsername(), user);
	}


}