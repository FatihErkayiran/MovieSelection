package com.cognixia.jump;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String URL="jdbc:mysql://localhost:3306/sakila?serverTimezone=EST5EDT";
	private static final String USERNAME="root";
	private static final String PASSWORD="root";
	
	//the only connection we have, will always return this connection
	private static Connection connection=null;
	
	private static void makeConnection() {
		try {
			//loads in Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();		}
		
	}
	
	public static Connection getConnection() {
		if (connection==null) { // if connection not established ,create it before return
			makeConnection();
			
		}
		return connection;
	}

}
