package com.demo.app.configuration.internationalization;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.security.UserAuthentication;

@Component
public class MessageSourceLocale extends ReloadableResourceBundleMessageSource {

	public String getMessage(String arg0, Object[] arg1) {
		Locale locale = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getLocale();
		return super.getMessage(arg0, arg1, locale);
	}

}
