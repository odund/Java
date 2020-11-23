package Dao;

/**
 * This DBConnect class implements a method to use the static JDBC method
 * DriverManager.getConnection() to get a connection to a database.
 */

import java.sql.Connection; // Interface Connection session
import java.sql.DriverManager; // The basic service for managing a set of JDBC drivers
import java.sql.SQLException; // An exception that provides information on a database access error or other errors

public class DBConnect {

	protected Connection connection;

	public Connection getConnection() {
		return connection;
	}
	
	// Code Database URL
	static final String url = "jdbc:mysql://localhost:3306/mydatabase";
	// Database credintials
	static final String username = "user", password = "password";

	/**
	 * This method creates a reference to a Connection object
	 * 
	 * @exception SQLException if getConnection method fails to load an appropriate
	 *                         driver for the database
	 */
	public DBConnect() {

		// Create reference to a Connection object
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			// e.printStackTrace();
			System.exit(-1);
		}
	}

}
