package com.demo.app.configuration;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.web.context.ServletConfigAware;

import com.demo.app.configuration.swagger.IterableFormModel;
import com.demo.app.util.Constants;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;

/**
 * Provides the location of the packages with the Rest resources and @Providers
 *
 * @author coke
 *
 */
public class JerseyConfig extends ResourceConfig implements ServletConfigAware {

	private ServletConfig servletConfig;

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages(Constants.CONTROLLER_PACKAGE);
		packages(Constants.CONFIGURATION_PACKAGE);
		register(JacksonFeature.class);
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
	}

	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig = servletConfig;
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

		Swagger swagger = new Swagger();
		// JWT Security
		ApiKeyAuthDefinition apiKey = new ApiKeyAuthDefinition();
		apiKey.setIn(In.HEADER);
		apiKey.setName("X-AUTH-TOKEN");
		apiKey.setType("apiKey");
		apiKey.setDescription("JWT TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI5ZTYyYjM5OS02MDdiLTRkMGItOTg1Ny0xZjBmYzM1ZjJhNmIiLCJzdWIiOiJrcmlzaG5hIiwiaWF0IjoxNDkzODE5MTU1LCJleHAiOjE1MjUzNTUxNTV9.H5bCw0UcIQegztc1mEhD0EPxdeaBvV8xOfZjtlPPCjwciqV2DcBsOZa3KxyamGwJCTj_wm9U0wTMw9J0YlfXGw");
		swagger.securityDefinition("JWT", apiKey);
		// Iterable<Form> not recognized by Swagger
		IterableFormModel model = new IterableFormModel();
		swagger.addDefinition("IterableForm", model);
		new SwaggerContextService().withServletConfig(servletConfig).updateSwagger(swagger);
	}
}
