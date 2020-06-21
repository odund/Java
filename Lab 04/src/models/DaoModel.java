package models;

/**
 * This DaoModel class implements methods to create a database table, to insert BankRecords into table and
 * to retrieve records to display on the screen
 * 
 * Lab 04
 * File Name: DaoModel.java
 * @author Oleg Grigoryan
 * @since 2019-04-11
 * 
 */

import java.sql.SQLException;			// An exception that provides information on a database access error or other errors
import java.sql.Statement;				// The object used for executing a static SQL statement and returning the results it produces
import java.sql.ResultSet;				// A table of data representing a database result set
//import java.sql.PreparedStatement;	// An object that represents a precompiled SQL statement


import records.BankRecords;

public class DaoModel {
	
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;
	String sql = null;
	
	// constructor
	public DaoModel() {
		conn = new DBConnect();
	}
	
	/**
	 * The createTable method creates database table. The table includes the 
	 * pid, id, income and pep fields during table setup. A PRIMARY KEY included
	 * to ensure the record uniqueness.
	 * @exception SQLException if createTable method fails
	 */
	public void createTable() {	
		try {
			// Open a connection
			System.out.println("Connecting to database to create Table...");
			System.out.println("Connected database successfully...");
			
			// Execute create query
			System.out.println("Creating table in given database...");
			
			stmt = conn.connect().createStatement();
			
			// Clear TABLE in database so the records don't accumulate with each application run
			sql = "DELETE FROM O_GRIG_tab where pid > 0";
            stmt.executeUpdate(sql);
			sql = "ALTER TABLE O_GRIG_tab AUTO_INCREMENT = 1";
            stmt.executeUpdate(sql);
						
			sql = "CREATE TABLE IF NOT EXISTS O_GRIG_tab " + 
					"(pid INTEGER NOT NULL AUTO_INCREMENT, " + 
					" id VARCHAR(10), " + 
					" income numeric(8,2), " + 
					" pep VARCHAR(3), " + 
					" PRIMARY KEY ( pid ))";
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			
		} catch (SQLException se) {		// Handle errors for JDBC
			System.err.println("DaoModel::createTable: An SQLException was caught!");
			se.printStackTrace();
			
		} finally {
			try 
			{
				// Close DB Connection
				// stmt.close();
				conn.connect().close();
			}
			catch (SQLException e) 
			{
			System.err.println("DaoModel::createTable: An SQLException was caught while closing db connection!");
			e.printStackTrace();
			}
		}
	}
	
	/**
	 * The insertRecords method allows to pass array of BankRecords objects, which will
	 * allow for the insertion of all the id, income and pep data from BankRecords array 
	 * into database table
	 * @param robjs The array of BankRecords objects
	 * @exception SQLException if insertRecords method fails
	 */
	public void insertRecords(BankRecords[] robjs) {
		try {
			// Execute a query
			System.out.println("Inserting records into table...");
			stmt = conn.connect().createStatement();
			
			// For debugging only. Remove after testing
			System.out.printf("The total number of records inserting is %d \n", robjs.length);
			
			// Include all object data to the database table
			for (int i = 0; i < robjs.length; ++i) {
				
				sql = "INSERT INTO O_GRIG_tab " + 
						"(id, income, pep) " +
						"VALUES ('" + 
						robjs[i].getId() + "', " + 
						robjs[i].getIncome() + ", '" + 
						robjs[i].getPep() + "')";
				stmt.executeUpdate(sql);
			}
			System.out.println("Records inserted...");
			
		} catch (SQLException se) {		// Handle errors for JDBC
			System.err.println("DaoModel::insertRecords: An SQLException was caught!");
			se.printStackTrace(); 
			
			} finally {
				try 
				{
					// Close DB Connection
					// stmt.close();
					conn.connect().close();
				}
				catch (SQLException e) 
				{
				System.err.println("DaoModel::insertRecords: An SQLException was caught while closing db connection!");
				e.printStackTrace();
				}
			}
	}
	
	/**
	 * The insertRecords method allows to pass array of BankRecords objects, which will
	 * allow for the insertion of all the id, income and pep data from BankRecords array 
	 * into database table
	 * @return rs The table of data representing a database result set
	 * @exception SQLException if retrieveRecords method fails
	 */
	public ResultSet retrieveRecords() {
		
		ResultSet rs = null, console = null;
		Statement cnslStmt = null;
		
		try {
			System.out.println("Retrieving records from table...");
			stmt = conn.connect().createStatement();
			cnslStmt = conn.connect().createStatement();
		
			//String sql = "SELECT * from O_GRIG_tab";
			String sql = "SELECT pid, id, income, pep from O_GRIG_tab order by pep desc";
			rs = stmt.executeQuery(sql);
			String cnsl = "SELECT pid, id, income, pep from O_GRIG_tab order by pep desc";
			console = cnslStmt.executeQuery(cnsl);
			System.out.println("Records retrieved...");
			
			// Display the Loan Detail on console window
			System.out.println("Printing Loan Details on console output...\n");
			System.out.println("\t\t ------------");
			System.out.println("\t\t|Loan Details|");
			System.out.println("\t\t ------------");
			System.out.println("PID\t\tID\t\tINCOME\t\tPEP");
			System.out.println("---\t\t--\t\t------\t\t---");
			// Print records on console
			while (console.next()) {
				String pid = console.getObject(1).toString();
				String id = console.getObject(2).toString();
				String income = console.getObject(3).toString();
				String pep = console.getObject(4).toString();
				System.out.format("%-10s\t%-10s\t%-10s\t%-10s\n", pid, id, income, pep);
			}
	
			} catch (SQLException se) {		// Handle errors for JDBC
				System.err.println("DaoModel::retrieveRecords: An SQLException was caught!");
				se.printStackTrace(); 
				} finally {
					try 
					{
						// Close DB Connection
						// stmt.close();
						conn.connect().close();
					}
					catch (SQLException e) 
					{
					System.err.println("DaoModel::retrieveRecords: An SQLException was caught while closing db connection!");
					e.printStackTrace();
					}
				}
			return rs;
	}
}
