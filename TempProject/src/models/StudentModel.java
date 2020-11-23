package models;

/**
 * This StudentModel class
 */

import Dao.DBConnect;
import java.sql.Statement; // The object used for executing a static SQL statement and returning the results it produces
//import java.sql.ResultSet;				// A table of data representing a database result set
//import java.sql.SQLException;

public class StudentModel {

	private int studentId;
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public StudentModel() {
		conn = new DBConnect();
	}

	public int getStudentId() {
		return studentId;
	}

	public void setTeacherId(int studentId) {
		this.studentId = studentId;
	}

	public void insertRecord(int studentId) {
		/*
		 * try { setStudentId(studentId); // Execute a query
		 * System.out.println("Inserting record into the table..."); stmt =
		 * conn.getConnection().createStatement(); String sql = null;
		 * 
		 * // Include data to the database table
		 * 
		 * sql = " insert into jpapa_accounts(cid, balance) values('" + studentId +
		 * "')";
		 * 
		 * stmt.executeUpdate(sql); conn.getConnection().close();
		 * 
		 * System.out.println("StudentID " + studentId);
		 * 
		 * } catch (SQLException se) { se.printStackTrace(); }
		 */
	}

}
