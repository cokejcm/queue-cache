package com.demo.app.configuration;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.security.access.AccessDeniedException;

/**
 * Avoids Jersey to intercept the AccessDeniedException and rethrow it as
 * ServletException
 */
@Provider
public class AccessDeniedMapper implements ExceptionMapper<AccessDeniedException> {
	@Override
	public Response toResponse(AccessDeniedException e) {
		return Response.status(401).build();
	}
}