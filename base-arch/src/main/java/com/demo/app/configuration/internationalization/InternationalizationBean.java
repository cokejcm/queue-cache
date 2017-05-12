package com.demo.app.configuration.internationalization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InternationalizationBean extends WebMvcConfigurerAdapter {

	@Bean
	public MessageSourceLocale messageSource() {
		MessageSourceLocale messageSource = new MessageSourceLocale();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
