package com.computerDatabase.excilys.spring;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(value = {
		"com.computerDatabase.excilys.dao",
		"com.computerDatabase.excilys.service",
		"com.computerDatabase.excilys.mapper"
		})

public class MainConfiguration {
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public HikariDataSource dataSource() {
		return (HikariDataSource) DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
}