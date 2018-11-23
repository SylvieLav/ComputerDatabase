package com.computerDatabase.excilys.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private Connection connection;
    private Properties prop = new Properties();
    
    private DbConnection() {
    }
    
    private static final DbConnection INSTANCE = new DbConnection();
    
    public static DbConnection getInstance() {
		return INSTANCE;
	}
   
    private Properties getProperties() {
    	
        try (InputStream input = new FileInputStream("resources/config.properties")) {
        	prop.load(input);
        } catch (IOException e) {
        	e.printStackTrace();
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
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

}
