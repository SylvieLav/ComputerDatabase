package com.computerDatabase.excilys.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.slf4j.*;

public class DbConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(DbConnection.class);
    private Connection connection;
    private Properties prop = new Properties();
    
    private DbConnection() {
    }
    
    private static final DbConnection INSTANCE = new DbConnection();
    
    public static DbConnection getInstance() {
		return INSTANCE;
	}
   
    private Properties getProperties() {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {
        	prop.load(input);
        } catch (IOException e) {
        	LOGGER.error("Could not load the properties in DbConnection.getProperties() !");
        }
        
        return prop;
    }

    public Connection connect() {
    	getProperties();
        if (connection == null) {
            try {
                Class.forName(prop.getProperty("DatabaseDriver"));
                connection = DriverManager.getConnection(prop.getProperty("Url"), getProperties());
            } catch (ClassNotFoundException | SQLException e) {
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
