package com.demo.app.util;

import com.hazelcast.config.MaxSizeConfig;

public final class Constants {

	// Paths
	public static final String SERVICE_PACKAGE = "com.demo.app.service";
	public static final String DOMAIN_PACKAGE = "com.demo.app.domain";
	// Token expiration time in days
	public static int EXPIRATION_DAYS = 365;
	// Token secret key
	public static final String TOKEN_KEY = "?ymsj4xmwmth*uWNtmV{C]8kg8f&kU";
	// Token header attribute
	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	// Hazelcast
	public static final MaxSizeConfig MAX_SIZE_CONFIG = new MaxSizeConfig(20, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_PERCENTAGE);
	// i18n
	public static final String COOKIE_LANGUAGE = "locale-cookie";
	public static final String DEFAULT_COUNTRY_CODE = "en";
	//Extra Info params in UserAuthentication
	public static final String LOCALE = "locale";
}
