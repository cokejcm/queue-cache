package com.demo.app.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.demo.app.configuration.ContextProvider;
import com.google.common.base.Preconditions;

@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService tokenAuthenticationService;

	public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = Preconditions.checkNotNull(tokenAuthenticationService);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		UserAuthentication authentication = (UserAuthentication) tokenAuthenticationService.getAuthentication((HttpServletRequest) request);
		// Extra Information
		ContextExtraInfo extraInfo=null;
		try {
			extraInfo = ContextProvider.getBean(ContextExtraInfo.class);
			if (authentication != null){
				authentication.addExtraInfo(extraInfo.getExtraInfo1Name(), extraInfo.getExtraInfo1(authentication));
			}
		} catch (NoSuchBeanDefinitionException e) {
			//Do nothing
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		SecurityContextHolder.clearContext();
	}
}
