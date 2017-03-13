package com.demo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;

@Configuration
@Profile("cache")
public class HazelcastConfig {

	@Bean
	public Config hazelCastConfig() {

		Config config = new Config();
		config.setInstanceName("hazelcast-cache");
		// Management center
		ManagementCenterConfig center = new ManagementCenterConfig();
		center.setEnabled(true);
		center.setUrl("http://localhost:9091/mancenter");
		config.setManagementCenterConfig(center);

		/*
		 * MapConfig allMessages = new MapConfig(); // //
		 * allMessages.setTimeToLiveSeconds(20); // //
		 * allMessages.setEvictionPolicy(EvictionPolicy.LFU);
		 * allMessages.setStatisticsEnabled(true);
		 * allMessages.setName("AllMessages"); config.addMapConfig(allMessages);
		 * config.setProperty("hazelcast.logging.type", "log4j"); //
		 * 
		 * MapConfig messageById = new MapConfig(); // //
		 * messageById.setTimeToLiveSeconds(20); // //
		 * messageById.setEvictionPolicy(EvictionPolicy.LFU);
		 * messageById.setStatisticsEnabled(true);
		 * messageById.setName("messageByIdMap");
		 * config.addMapConfig(messageById);
		 * config.setProperty("hazelcast.logging.type", "log4j");
		 */

		return config;
	}
}
