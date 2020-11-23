package models;

/**
 * This LoginModel class
 */

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import Dao.DBConnect;

public class LoginModel extends DBConnect {

	private Boolean admin810;
//	private int id;

	public Boolean isAdmin() {
		return admin810;
	}

	public void setAdmin(Boolean admin810) {
		this.admin810 = admin810;
	}

	/*
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public int getId() { return id; }
	 */
	public Boolean getCredentials(String username, String password) {

		// Temporarily
		if ((username.equals("admin810") && password.equals("admin"))
				|| (username.equals("teacher810") && password.equals("teacher"))
				|| (username.equals("student810") && password.equals("student"))) {
			return true;
		}

		return false;

		/*
		 * String query =
		 * "SELECT * FROM login WHERE username = ? and password = ?;";
		 * try(PreparedStatement stmt = connection.prepareStatement(query)) { //
		 * temporarily debug print System.out.println("LoginModel: In query");
		 * 
		 * stmt.setString(1, username); stmt.setString(2, password); ResultSet rs =
		 * stmt.executeQuery(); if(rs.next()) { setAdmin(rs.getBoolean("admin810")); //
		 * setId(rs.getInt("id"); return true; } }catch (SQLException e) {
		 * System.out.println("Error occurred while getting credentials from DBMS: " +
		 * e); e.printStackTrace(); } return false;
		 */
	}

}
