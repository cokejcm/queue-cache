package com.demo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;

@Configuration
@Profile("hazelcast")
public class HazelcastConfig {
	@Bean
	public Config hazelCastConfig() {

		Config config = new Config();
		config.setInstanceName("hazelcast-cache");

		MapConfig allUsersCache = new MapConfig();
		allUsersCache.setTimeToLiveSeconds(20);
		allUsersCache.setEvictionPolicy(EvictionPolicy.LFU);
		config.getMapConfigs().put("alluserscache",allUsersCache);
		//config.setProperty("hazelcast.logging.type","slf4j");

		MapConfig usercache = new MapConfig();
		usercache.setTimeToLiveSeconds(20);
		usercache.setEvictionPolicy(EvictionPolicy.LFU);
		config.getMapConfigs().put("usercache",usercache);

		return config;
	}
}
