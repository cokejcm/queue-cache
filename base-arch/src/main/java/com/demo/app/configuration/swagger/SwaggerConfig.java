package com.demo.app.configuration.swagger;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.demo.app.util.Constants;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Generates swagger.json
 *
 * @author coke
 *
 */
@Configuration
public class SwaggerConfig extends ResourceConfig {

	@PostConstruct
	public void init() {
		this.configureSwagger();
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setConfigId("app");
		beanConfig.setTitle("App Rest Services");
		beanConfig.setVersion("v1");
		beanConfig.setHost(Constants.HOST + ":" + Constants.PORT);
		beanConfig.setContact("Coke");
		beanConfig.setSchemes(new String[] { "http", "https" });
		beanConfig.setBasePath(Constants.CONTEXT);
		beanConfig.setResourcePackage("com.demo.app.controller");
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
	}

}
