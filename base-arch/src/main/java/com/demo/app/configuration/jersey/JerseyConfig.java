package com.demo.app.configuration.jersey;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

import com.demo.app.configuration.swagger.FormDataBodyPartModel;
import com.demo.app.configuration.swagger.IterableEntityModel;
import com.demo.app.util.Constants;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Model;
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

	@Autowired
	private Environment environment;
	@Autowired
	private ConfigProperties configProperties;

	private void registry(){
		register(RequestContextFilter.class);
		register(MultiPartFeature.class);
		register(JacksonFeature.class);
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
	}

	public JerseyConfig() {
		registry();
		// Register components manually due to a bug in Jersey and Spring Boot. It replaces "packages"
		scan(Constants.CONFIGURATION_PACKAGE);
		scan(Constants.CONTROLLER_PACKAGE);

	}

	public JerseyConfig(boolean scanControllers) {
		registry();
		// Register components manually due to a bug in Jersey and Spring Boot. It replaces "packages"
		scan(Constants.CONFIGURATION_PACKAGE);
		if (scanControllers){
			scan(Constants.CONTROLLER_PACKAGE);
		}
	}

	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig = servletConfig;
	}

	@PostConstruct
	public void init() {
		if (Arrays.stream(this.environment.getActiveProfiles()).anyMatch("dev"::equals) && Arrays.stream(this.environment.getActiveProfiles()).anyMatch("swagger"::equals)) {
			this.configureSwagger();
		}
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setConfigId(Constants.CONFIG_ID);
		beanConfig.setTitle(Constants.TITLE);
		beanConfig.setVersion(Constants.VERSION);
		beanConfig.setHost(configProperties.getJsonSwaggerHost() + ":" + Constants.PORT);
		beanConfig.setContact(Constants.CONTACT);
		beanConfig.setSchemes(Constants.SCHEMAS);
		beanConfig.setBasePath(Constants.COMPLETE_CONTEXT);
		beanConfig.setResourcePackage(Constants.CONTROLLER_PACKAGE);
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
		Swagger swagger = new Swagger();
		// JWT Admin Role Security
		ApiKeyAuthDefinition apiKey = new ApiKeyAuthDefinition();
		apiKey.setIn(In.HEADER);
		apiKey.setName("X-AUTH-TOKEN");
		apiKey.setType("apiKey");
		apiKey.setDescription("ADMIN_ROLE JWT TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI5ZTYyYjM5OS02MDdiLTRkMGItOTg1Ny0xZjBmYzM1ZjJhNmIiLCJzdWIiOiJrcmlzaG5hIiwiaWF0IjoxNDkzODE5MTU1LCJleHAiOjE1MjUzNTUxNTV9.H5bCw0UcIQegztc1mEhD0EPxdeaBvV8xOfZjtlPPCjwciqV2DcBsOZa3KxyamGwJCTj_wm9U0wTMw9J0YlfXGw");
		swagger.addSecurityDefinition("ADMIN_ROLE", apiKey);
		// JWT App Role Security
		apiKey = new ApiKeyAuthDefinition();
		apiKey.setIn(In.HEADER);
		apiKey.setName("X-AUTH-TOKEN");
		apiKey.setType("apiKey");
		apiKey.setDescription("APP_ROLE JWT TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI3MjVhM2U3Ni0wOGFlLTRhZDAtOGM2Yy00ZTY5YTM0YTJjYmMiLCJzdWIiOiJzdXBlcnVzZXIiLCJpYXQiOjE1MDQzODg2NzQsImV4cCI6MTUzNTkyNDY3NH0.2waRMVDB7O6-Pvl-NMtSvW2VSybK5pYdgefeNAQaUSIZPP24Ia88-TFyj9HClH15wmj35lfz-v5kkyXl58x3Kw");
		swagger.addSecurityDefinition("APP_ROLE", apiKey);
		// Iterable<AnyEntity> not recognized by Swagger
		Model model = new IterableEntityModel();
		swagger.addDefinition("IterableEntity", model);
		swagger.addDefinition("IterableUserOrganization", model);
		swagger.addDefinition("IterableSupplier", model);
		swagger.addDefinition("IterableCompany", model);
		swagger.addDefinition("IterableServiceType", model);
		swagger.addDefinition("IterableDocument", model);
		Model model2 = new FormDataBodyPartModel();
		swagger.addDefinition("FormDataBodyPart", model2);
		new SwaggerContextService().withServletConfig(servletConfig).updateSwagger(swagger);
	}

	protected void scan(String... packages) {
		for (String pack : packages) {
			Reflections reflections = new Reflections(pack);
			reflections.getTypesAnnotatedWith(Component.class)
			.parallelStream()
			.forEach((clazz) -> {
				register(clazz);
			});
			reflections.getTypesAnnotatedWith(Provider.class)
			.parallelStream()
			.forEach((clazz) -> {
				register(clazz);
			});
		}
	}
}
