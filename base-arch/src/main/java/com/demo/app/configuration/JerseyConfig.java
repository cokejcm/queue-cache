package com.demo.app.configuration;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.demo.app.util.Constants;

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

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages(Constants.CONTROLLER_PACKAGE);
		packages(Constants.CONFIGURATION_PACKAGE);
		register(JacksonFeature.class);
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
	}

	@PostConstruct
	public void init() {
		this.configureSwagger();
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setConfigId(Constants.CONFIG_ID);
		beanConfig.setTitle(Constants.TITLE);
		beanConfig.setVersion(Constants.VERSION);
		beanConfig.setHost(Constants.HOST + ":" + Constants.PORT);
		beanConfig.setContact(Constants.CONTACT);
		beanConfig.setSchemes(Constants.SCHEMAS);
		beanConfig.setBasePath(Constants.COMPLETE_CONTEXT);
		beanConfig.setResourcePackage(Constants.CONTROLLER_PACKAGE);
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
	}
}
