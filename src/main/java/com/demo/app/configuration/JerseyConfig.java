package com.demo.app.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Provides the location of the packages with the Rest resources
 * @author coke
 *
 */
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages("com.demo.app.controller");
	}
}
