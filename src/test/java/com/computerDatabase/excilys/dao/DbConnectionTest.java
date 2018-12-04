package com.computerDatabase.excilys.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.*;

public class DbConnectionTest {
	private Connection connection;
    private Properties prop = new Properties();
    
    @Test
    public Properties getProperties() {
    	try (InputStream input = new FileInputStream("resources/testConfig.properties")) {
        	prop.load(input);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    	
    	return prop;
    }
    
	@Test
	public void testConnect() {
		getProperties();
        if (connection == null) {
            try {
                Class.forName(prop.getProperty("DatabaseDriver"));
                connection = DriverManager.getConnection(prop.getProperty("Url"), getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Test
	public void testDisconnect() {
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
