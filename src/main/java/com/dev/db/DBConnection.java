package com.dev.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {
            // Load database properties from file
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties");
            props.load(input);
            
            // Get connection details from properties
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage());
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
