package com.demo.app.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.google.common.base.Preconditions;

@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService tokenAuthenticationService;

	public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
		System.out.println("filter constructor");
		this.tokenAuthenticationService = Preconditions.checkNotNull(tokenAuthenticationService);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("filter");
		Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) request);
		SecurityContextHolder.getContext()
				.setAuthentication(authentication);
		filterChain.doFilter(request, response);
		SecurityContextHolder.getContext()
				.setAuthentication(null);
	}
}
