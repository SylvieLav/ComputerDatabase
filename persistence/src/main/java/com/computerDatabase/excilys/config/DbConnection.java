package com.computerDatabase.excilys.config;

import java.io.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.zaxxer.hikari.*;

@Configuration
@ComponentScan(value = {
		"com.computerDatabase.excilys.cli",
		"com.computerDatabase.excilys.dao",
		"com.computerDatabase.excilys.dto",
		"com.computerDatabase.excilys.mapper",
		"com.computerDatabase.excilys.model",
		"com.computerDatabase.excilys.service",
		"com.computerDatabase.excilys.validator"
		})
public class DbConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(DbConnection.class);

	@Bean
	public DataSource getDataSource() {
		Properties prop = new Properties();
		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {
			prop.load(input);
		} catch (IOException e) {
			LOGGER.error("Could not load the properties in DbConnection.getProperties() !");
		}

		HikariConfig config = new HikariConfig();
		config.setDriverClassName(prop.getProperty("DatabaseDriver"));
		config.setJdbcUrl(prop.getProperty("Url"));
		config.setUsername(prop.getProperty("User"));
		config.setPassword(prop.getProperty("Password"));
		config.setMaximumPoolSize(Integer.parseInt(prop.getProperty("MaxPool")));
		config.addDataSourceProperty("cachePrepStmts", prop.getProperty("CachePrepStmts"));
		config.addDataSourceProperty("prepStmtCacheSize", prop.getProperty("PrepStmtCacheSize"));
		config.addDataSourceProperty("prepStmtCacheSqlLimit", prop.getProperty("PrepStmtCacheSqlLimit"));

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}

	Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.show_sql", "true");

		return properties;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(ds);
		session.setHibernateProperties(hibernateProperties());
		session.setPackagesToScan(new String[] { "com.computerDatabase.excilys.model" });

		return session;
	}
}
