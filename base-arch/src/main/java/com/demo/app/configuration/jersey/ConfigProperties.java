package com.demo.app.configuration.jersey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class ConfigProperties {

	private String jsonSwaggerHost;

	public String getJsonSwaggerHost() {
		return jsonSwaggerHost;
	}

	public void setJsonSwaggerHost(String jsonSwaggerHost) {
		this.jsonSwaggerHost = jsonSwaggerHost;
	}

}