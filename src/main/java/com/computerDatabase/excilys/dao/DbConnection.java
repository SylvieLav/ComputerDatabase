package com.computerDatabase.excilys.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.slf4j.*;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.*;

@Component
public class DbConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(DbConnection.class);

	private static HikariDataSource ds;

	private Connection connection;
	
	private DbConnection() {}

	static {
		Properties prop = new Properties();
		try (InputStream input = DbConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
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

		ds = new HikariDataSource(config);
	}

	public static HikariDataSource getDataSource() {
		return ds;
	}

	public Connection connect() {
		if (connection == null) {
			try {
				connection = ds.getConnection();
			} catch (SQLException e) {
				LOGGER.error("Could not instantiate the connection in DbConnection.connect() !");
			}
		}
		
		return connection;
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				LOGGER.error("Could not close the connection in DbConnection.disconnect() !");
			}
		}
	}
}
