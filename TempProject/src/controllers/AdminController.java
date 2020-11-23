package controllers;

/**
 * This AdminController class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException; // An exception that provides information on a database access error or other errors
import java.util.Random; // To access Random class

import Dao.DBConnect;
import java.sql.Statement;

public class AdminController {

	@FXML
	private Pane pane1;

	@FXML
	private Pane pane2;

	@FXML
	private Pane pane3;

	@FXML
	private Pane pane4;

	@FXML
	private Label lblRegError;

	@FXML
	private Label lblViewError;

	@FXML
	private Label lblViewFullName;

	@FXML
	private Label lblViewDob;

	@FXML
	private Label lblViewGender;

	@FXML
	private Label lblViewAge;

	@FXML
	private Label lblViewHomeAddr;

	@FXML
	private Label lblViewEmerCont;

	@FXML
	private Label lblUpdError;

	@FXML
	private Label lblDelError;

	@FXML
	private TextField txtFullName;

	@FXML
	private TextField txtDateOfBirth;

	@FXML
	private TextField txtGender;

	@FXML
	private TextField txtAge;

	@FXML
	private TextField txtHomeAddress;

	@FXML
	private TextField txtEmergencyContact;

	@FXML
	private TextField txtViewStudentUniqueId;

	@FXML
	private TextField txtStudentUniqueId;

	public void admin() {

	}

	public void registerNewStudent() {
		pane1.setVisible(true);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(false);
	}

	public void viewStudentProfile() {
		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
		pane4.setVisible(false);
	}

	public void updateStudentRecord() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);
		pane4.setVisible(false);
	}

	public void checkStudentFinanceRecord() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(true);
	}

	// DB object
	DBConnect conn = null;
	Statement stmt = null;

	public AdminController() {
		conn = new DBConnect();
	}

	public void submit() {

		try {

			lblRegError.setText("");

			if ((txtFullName.getText() == null || txtFullName.getText().trim().equals(""))
					|| (txtDateOfBirth.getText() == null || txtDateOfBirth.getText().trim().equals(""))
					|| (txtGender.getText() == null || txtGender.getText().trim().equals(""))
					|| (txtAge.getText() == null || txtAge.getText().trim().equals(""))
					|| (txtHomeAddress.getText() == null || txtHomeAddress.getText().trim().equals(""))
					|| (txtEmergencyContact.getText() == null || txtEmergencyContact.getText().trim().equals(""))) {
				lblRegError.setText("The fields cannot be empty or spaces");
				return;
			}

			System.out.println("AdminController::submit: Registering new student...");

			String studentUniqueId = createId("SID", 1000);
			String fullName = txtFullName.getText();
			String dateOfBirth = txtDateOfBirth.getText();
			String gender = txtGender.getText();
			String age = txtAge.getText();
			String homeAddress = txtHomeAddress.getText();
			String emergencyContact = txtEmergencyContact.getText();

			System.out.println("AdminController::submit: Printing registered student's info:");
			System.out.println("studentUniqueId: " + studentUniqueId);
			System.out.println("fullName: " + fullName);
			System.out.println("dateOfBirth: " + dateOfBirth);
			System.out.println("gender: " + gender);
			System.out.println("age: " + age);
			System.out.println("homeAddress: " + homeAddress);
			System.out.println("emergencyContact: " + emergencyContact);

			System.out.println("Inserting new student into the Table.class..");

			stmt = conn.getConnection().createStatement();
			String sql = null;

			sql = "insert into stud_prof(studuniqid, studfullname, studdob, studgender, studage, studaddr, studemer) values ('"
					+ studentUniqueId + "','" + fullName + "','" + dateOfBirth + "','" + gender + "','" + age + "','"
					+ homeAddress + "','" + emergencyContact + "')";

			stmt.executeUpdate(sql);

			System.out.println("New student data inserted..");

			conn.getConnection().close();

		} catch (SQLException e) {
			System.out.println("AdminController::submit: Error occurred while creating student account: " + e);
			e.printStackTrace();
		}
	}

	public void viewProfile() {

		try {

			lblViewError.setText("");
			lblViewFullName.setText("");
			lblViewDob.setText("");
			lblViewGender.setText("");
			lblViewAge.setText("");
			lblViewHomeAddr.setText("");
			lblViewEmerCont.setText("");

			if ((txtViewStudentUniqueId.getText() == null || txtViewStudentUniqueId.getText().trim().equals(""))) {
				lblViewError.setText("The field cannot be empty or spaces");
				return;
			}

			String studentUniqueId = txtViewStudentUniqueId.getText();

			System.out.println("Student Unique ID: " + studentUniqueId);
			System.out.println("Retrieving student's profile...");

			stmt = conn.getConnection().createStatement();
			String sql = null, dbViewUniqueId = null;
			ResultSet rs = null;
			String fullName = null, dateOfBirth = null, gender = null, age = null, homeAddress = null,
					emergencyContact = null;

			sql = "select studuniqid, studfullname, studdob, studgender, studage, studaddr, studemer from stud_prof where studuniqid = '"
					+ studentUniqueId + "'";

			rs = stmt.executeQuery(sql);
			
			// checking if ResultSet is empty 
			if (rs.next() == false) { 
				System.out.println("ResultSet is empty in Java"); 
			} else {
				do {
					String data =rs.getString("emp_name");
					System.out.println(data);
				} while (rs.next());
			  }

			// get row data from table!
			while (rs.next()) {
				dbViewUniqueId = rs.getString(1);
				fullName = rs.getString(2);
				dateOfBirth = rs.getString(3);
				gender = rs.getString(4);
				age = rs.getString(5);
				homeAddress = rs.getString(6);
				emergencyContact = rs.getString(7);
			}

			lblViewFullName.setText(fullName);
			lblViewDob.setText(dateOfBirth);
			lblViewGender.setText(gender);
			lblViewAge.setText(age);
			lblViewHomeAddr.setText(homeAddress);
			lblViewEmerCont.setText(emergencyContact);

			System.out.println("studentUniqueId: " + dbViewUniqueId);
			System.out.println("fullName: " + fullName);
			System.out.println("dateOfBirth: " + dateOfBirth);
			System.out.println("gender: " + gender);
			System.out.println("age: " + age);
			System.out.println("homeAddress: " + homeAddress);
			System.out.println("emergencyContact: " + emergencyContact);

			System.out.println("Student profile has been retrieved..");

			conn.getConnection().close();

		} catch (SQLException e) {
			System.out.println("AdminController::submit: Error occurred while creating student account: " + e);
			e.printStackTrace();
		}
	}

	public void update() {

		// TO DO

		/*
		 * try {
		 * 
		 * System.out.println("AdminController::update:Update button pressed");
		 * 
		 * lblUpdError.setText("");
		 * 
		 * txtFullName.setText(fullName); txtDateOfBirth.setText(dateOfBirth);
		 * txtGender.setText(gender); txtAge.setText(gender); txtAge.setText(age);
		 * txtHomeAddress.setText(homeAddress);
		 * txtEmergencyContact.setText(emergencyContact);
		 * txtStudentUniqueId.setText(studentUniqueId);
		 * 
		 * if ( (txtFullName.getText() == null ||
		 * txtFullName.getText().trim().equals("")) || (txtDateOfBirth.getText() == null
		 * || txtDateOfBirth.getText().trim().equals("")) || (txtGender.getText() ==
		 * null || txtGender.getText().trim().equals("")) || (txtAge.getText() == null
		 * || txtAge.getText().trim().equals("")) || (txtHomeAddress.getText() == null
		 * || txtHomeAddress.getText().trim().equals("")) ||
		 * (txtEmergencyContact.getText() == null ||
		 * txtEmergencyContact.getText().trim().equals("")) ) {
		 * lblUpdError.setText("The fields cannot be empty or spaces"); }
		 * 
		 * System.out.println("studentUniqueId: " + studentUniqueId);
		 * System.out.println("fullName: " + fullName);
		 * System.out.println("dateOfBirth: " + dateOfBirth);
		 * System.out.println("gender: " + gender); System.out.println("age: " + age);
		 * System.out.println("homeAddress: " + homeAddress);
		 * System.out.println("emergencyContact: " + emergencyContact);
		 * System.out.println("studentUniqueId: " + studentUniqueId);
		 * 
		 * } catch (Exception e) { System.out.
		 * println("AdminController::update: Error occurred while updating student account: "
		 * + e); e.printStackTrace(); }
		 */
	}

	public void delete() {

		try {

			lblDelError.setText("");

			if (txtStudentUniqueId.getText() == null || txtStudentUniqueId.getText().trim().equals("")) {
				lblDelError.setText("The Student Unique ID must be entered");
				return;
			}

			String studentUniqueId = txtStudentUniqueId.getText();
			stmt = conn.getConnection().createStatement();
			String sql = null, dbUniqueId = null, dbFullName = null;
			ResultSet rs = null;

			sql = "select studuniqid, studfullname from stud_prof where studuniqid = '" + studentUniqueId + "'";

			rs = stmt.executeQuery(sql);

			// get row data from table!
			while (rs.next()) {
				dbUniqueId = rs.getString(1);
				dbFullName = rs.getString(2);
			}

			System.out.println("AdminController::delete: studentUniqueId: " + studentUniqueId);
			System.out.println("AdminController::delete: dbUniqueId: " + dbUniqueId);
			System.out.println("AdminController::delete: dbFullName: " + dbFullName);

			if ((dbUniqueId == null) || dbUniqueId.equals(studentUniqueId)) {
				System.out.println("AdminController::delete: Inside notEqual pre-check");
				// clear label first
				lblDelError.setText("");
				lblDelError.setText("Student does not exist in database");
				conn.getConnection().close();
				return;
			}

			sql = "delete from stud_grade where studuniqid = '" + studentUniqueId + "'";
			stmt.executeUpdate(sql);
			sql = "delete from stud_prof where studuniqid = '" + studentUniqueId + "'";
			stmt.executeUpdate(sql);

			System.out.println("ID: " + studentUniqueId + " has beend deleted from database.");
			conn.getConnection().close();

		} catch (SQLException e) {
			System.out.println("AdminController::submit: Error occurred while creating student account: " + e);
			e.printStackTrace();
		}
		/*
		 * System.out.println("AdminController::update:Delete button pressed");
		 * lblUpdError.setText("");
		 * 
		 * studentUniqueId = txtStudentUniqueId.getText();
		 * 
		 * if ( txtStudentUniqueId.getText() == null ||
		 * txtStudentUniqueId.getText().trim().equals("") ) {
		 * lblUpdError.setText("The student unique ID field cannot be empty or spaces");
		 * } System.out.println("Deleted studentUniqueId: " + studentUniqueId);
		 */

	}

	public String createId(String pre, int number) {
		Random random = new Random();
		int index = random.nextInt(number);
		return pre + index + " ";
	}

}
