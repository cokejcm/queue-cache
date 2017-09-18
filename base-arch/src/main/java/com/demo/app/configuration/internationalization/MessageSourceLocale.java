package com.demo.app.configuration.internationalization;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.security.UserAuthentication;
import com.demo.app.util.Constants;

@Component
public class MessageSourceLocale extends ReloadableResourceBundleMessageSource {

	@Autowired
	private Environment environment;

	public String getMessage(String arg0, Object[] arg1) {
		Locale locale;
		//Cannot access to the Security Context from AOP-Async in the Maven tests
		if (Arrays.stream(this.environment.getActiveProfiles()).anyMatch("dev"::equals)){
			locale = Locale.ENGLISH;
		}
		else{
			locale = (Locale)((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getExtraInfo(Constants.LOCALE);
		}
		return super.getMessage(arg0, arg1, locale);
	}

}
