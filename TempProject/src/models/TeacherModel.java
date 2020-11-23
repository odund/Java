package models;

/**
 * This TeacherModel class
 */

import Dao.DBConnect;
import java.sql.Statement; // The object used for executing a static SQL statement and returning the results it produces
//import java.sql.ResultSet;				// A table of data representing a database result set
//import java.sql.SQLException;

public class TeacherModel {

	private int teacherId;
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public TeacherModel() {
		conn = new DBConnect();
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public void insertRecord(int teacherId) {
		/*
		 * try { setTeacherId(adminId); // Execute a query
		 * System.out.println("Inserting record into the table..."); stmt =
		 * conn.getConnection().createStatement(); String sql = null;
		 * 
		 * // Include data to the database table
		 * 
		 * sql = " insert into jpapa_accounts(cid, balance) values('" + teacherId +
		 * "')";
		 * 
		 * stmt.executeUpdate(sql); conn.getConnection().close();
		 * 
		 * System.out.println("AdminID " + teacherId);
		 * 
		 * } catch (SQLException se) { se.printStackTrace(); }
		 */
	}

}
