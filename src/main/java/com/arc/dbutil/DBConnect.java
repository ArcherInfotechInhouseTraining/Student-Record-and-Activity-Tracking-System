package com.arc.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static DBConnect instance;
	static Connection con = null;
	
	private final String jdbcURL  = "jdbc:mysql://localhost:3306/registration_db";
	private final String jdbcUsername = "root";
    private final String jdbcPassword = "Archer@1234";
    
    private DBConnect() throws SQLException {
    	con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    public static DBConnect getInstance() throws SQLException {
    	if(con==null) {
    		instance = new DBConnect();
    	}
    	return instance;
    }
    public Connection getConnection() {
    	return con;
    }
}
