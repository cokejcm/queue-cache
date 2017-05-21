package com.demo.app.configuration;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * Provides the location of the packages with the Rest resources and @Providers
 *
 * @author coke
 *
 */
@EnableHypermediaSupport(type = HAL)
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages("com.demo.app.controller");
		packages("com.demo.app.configuration");
		register(JacksonFeature.class);
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
	}

}
