package com.demo.app.configuration.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConfig {

	@Value("classpath:schema.sql")
	private Resource schemaScript;

	@Value("classpath:data.sql")
	private Resource dataScript;

	@Autowired
	private Environment env;

	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.addScript(dataScript);
		return populator;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}

}
