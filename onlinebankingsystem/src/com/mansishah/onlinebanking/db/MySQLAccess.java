package com.mansishah.onlinebanking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLAccess {

	private static MySQLAccess instance = new MySQLAccess();
	private final String dbSourceUrl = "jdbc:mysql://localhost/obs";
	private final String username = "root";
	private final String password = "Mansi143";
	private final String Driver_class = "com.mysql.cj.jdbc.Driver";
	
	private MySQLAccess() {
		/* Load mySql Drive */

		try {
			Class.forName(Driver_class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private Connection createConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(dbSourceUrl, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
}
