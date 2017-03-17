package com.demo.app.util;

import com.hazelcast.config.MaxSizeConfig;

public final class Constants {

	// Paths
	public static final String SERVICE_PACKAGE = "com.demo.app.service";
	public static final String DOMAIN_PACKAGE = "com.demo.app.domain";

	// Hazelcast
	public static final MaxSizeConfig MAX_SIZE_CONFIG = new MaxSizeConfig(20, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_PERCENTAGE);

}
