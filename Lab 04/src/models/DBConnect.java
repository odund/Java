package models;

/**
 * This DBConnect class implements a method to use the static JDBC method
 * DriverManager.getConnection() to get a connection to a database.
 * 
 * Lab 04
 * File Name: DBConnect.java
 * @author Oleg Grigoryan
 * @since 2019-04-11
 * 
 */

import java.sql.Connection;				// Interface Connection session
import java.sql.DriverManager;			// The basic service for managing a set of JDBC drivers
import java.sql.SQLException;			// An exception that provides information on a database access error or other errors

public class DBConnect {
	
	// Code Database URL
	static final String DB_URL = "jdbc:mysql://localhost:3306/bankloandatabase";
	// Database credintials
	static final String USER = "user", PASS = "password";
	
	/**
	 * This method returns a reference to a Connection object
	 * @return a reference to a Connection object
	 * @exception SQLException if getConnection method fails to load an appropriate driver for the database
	 */
	public Connection connect() throws SQLException {
		
		// Return reference to a Connection object
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
}
