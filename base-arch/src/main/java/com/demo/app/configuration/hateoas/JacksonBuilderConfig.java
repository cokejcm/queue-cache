package com.demo.app.configuration.hateoas;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonBuilderConfig {

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat("dd/MM/yyyy HH24:MM:SS"));
		b.failOnEmptyBeans(false);
		b.failOnUnknownProperties(false);
		return b;
	}

}
