package models;

/**
 * This AdminModel class
 */

import Dao.DBConnect;
import java.sql.Statement; // The object used for executing a static SQL statement and returning the results it produces
//import java.sql.ResultSet;				// A table of data representing a database result set
//import java.sql.SQLException;

public class AdminModel extends DBConnect {

	private int adminId;
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public AdminModel() {
		conn = new DBConnect();
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void insertRecord(int adminId) {
		/*
		 * try { setAdminId(adminId); // Execute a query
		 * System.out.println("Inserting record into the table..."); stmt =
		 * conn.getConnection().createStatement(); String sql = null;
		 * 
		 * // Include data to the database table
		 * 
		 * sql = " insert into jpapa_accounts(cid, balance) values('" + adminId + "')";
		 * 
		 * stmt.executeUpdate(sql); conn.getConnection().close();
		 * 
		 * System.out.println("AdminID " + adminId);
		 * 
		 * } catch (SQLException se) { se.printStackTrace(); }
		 */
	}

}
