package com.demo.app.configuration.internationalization;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.demo.app.util.Constants;

@Configuration
public class WebMvcConfigurerBean extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;
	@Value("${swagger.ui.host}")
	private String swaggerUiHost;
	@Value("${swagger.ui.port}")
	private String swaggerUiPort;

	@Bean
	public MessageSourceLocale messageSource() {
		MessageSourceLocale messageSource = new MessageSourceLocale();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// CORS For Swagger
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		if (Arrays.stream(this.environment.getActiveProfiles()).anyMatch("dev"::equals) && Arrays.stream(this.environment.getActiveProfiles()).anyMatch("swagger"::equals)) {
			registry.addMapping("/**")
					.allowedOrigins("http://" + Constants.HOST + ":" + Constants.PORT, "http://" + swaggerUiHost + ":" + swaggerUiPort)
					.allowedMethods("GET", "PUT", "POST", "DELETE");
		}
	}
}
