package com.demo.app.configuration.cache;

import java.util.Set;

import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.app.domain.Entity;
import com.demo.app.util.Constants;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;

@Configuration
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
		config.setProperty("hazelcast.logging.type", "log4j");
		// For every Cacheable domain bean, create and configure a new map
		Reflections reflections = new Reflections(Constants.SERVICE_PACKAGE);
		Set<Class<? extends Entity>> allClasses = reflections.getSubTypesOf(Entity.class);
		for (Class<? extends Entity> clazz : allClasses) {
			MapConfig entityMap = new MapConfig();
			entityMap.setEvictionPolicy(EvictionPolicy.LRU);
			entityMap.setName(clazz.getSimpleName());
			entityMap.setMaxSizeConfig(Constants.MAX_SIZE_CONFIG);
			entityMap.setStatisticsEnabled(true);
			config.addMapConfig(entityMap);
		}
		return config;
	}
}
