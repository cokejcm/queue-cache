package com.demo.app.configuration;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Provides the location of the packages with the Rest resources and @Providers
 *
 * @author coke
 *
 */
public class JerseyConfig extends ResourceConfig {

	@PostConstruct
	public void init() {
		// Register components where DI is needed
		this.configureSwagger();
	}

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages("com.demo.app.controller");
		packages("com.demo.app.configuration");
		register(JacksonFeature.class);
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
	}

	private void configureSwagger() {
		// Available at localhost:port/swagger.json
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setConfigId("springboot-jersey-swagger-docker-example");
		config.setTitle("Spring Boot + Jersey + Swagger + Docker Example");
		config.setVersion("v1");
		config.setContact("Coke");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath("/app/rest/**");
		config.setResourcePackage("com.demo.app.controller");
		config.setPrettyPrint(true);
		config.setScan(true);
	}

}
