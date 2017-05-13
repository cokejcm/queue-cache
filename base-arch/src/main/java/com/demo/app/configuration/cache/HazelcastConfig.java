package com.demo.app.configuration.cache;

import java.util.Set;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demo.app.domain.Entity;
import com.demo.app.util.Constants;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;

@Profile("cache")
@Configuration
public class HazelcastConfig {

	@Value("${hz.mancenter.url}")
	private String mancenterUrl;

	@Value("${hz.mancenter.logging.type}")
	private String mancenterLogType;

	@Value("${hz.mancenter.logging.value}")
	private String mancenterLogValue;

	@Bean
	public Config hazelCastConfig() {

		Config config = new Config();
		config.setInstanceName("hazelcast-cache");
		// Management center
		ManagementCenterConfig center = new ManagementCenterConfig();
		center.setEnabled(true);
		center.setUrl(this.mancenterUrl);
		config.setManagementCenterConfig(center);
		config.setProperty(this.mancenterLogType, this.mancenterLogValue);
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
