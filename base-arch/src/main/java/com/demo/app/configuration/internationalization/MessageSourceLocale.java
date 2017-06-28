package com.demo.app.configuration.internationalization;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.security.UserAuthentication;
import com.demo.app.util.Constants;

@Component
public class MessageSourceLocale extends ReloadableResourceBundleMessageSource {

	public String getMessage(String arg0, Object[] arg1) {
		Locale locale = (Locale)((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getExtraInfo(Constants.LOCALE);
		return super.getMessage(arg0, arg1, locale);
	}

}
