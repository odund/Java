package controllers;

/**
 * This TeacherController class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import Dao.DBConnect;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherController {

	@FXML
	private Pane pane1;

	@FXML
	private Pane pane2;

	@FXML
	private Pane pane3;

	@FXML
	private Pane pane4;

	@FXML
	private Label lblError;

	@FXML
	private Label lblStuAttError;

	@FXML
	private Label lblStuGraError;

	@FXML
	private Label lblViewError;

	@FXML
	private Label lblViewStuGraError;

	@FXML
	private Label lblViewProfFullName;

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
	private Label lblViewLab1Gr;

	@FXML
	private Label lblViewLab2Gr;

	@FXML
	private Label lblViewMidtermExGr;

	@FXML
	private Label lblViewLab3Gr;

	@FXML
	private Label lblViewLab4Gr;

	@FXML
	private Label lblViewFinalExGr;

	@FXML
	private Label lblViewFinalScore;

	@FXML
	private TextField txtAttFullName;

	@FXML
	private TextField txtFullName;

	@FXML
	private TextField txtWeek1;

	@FXML
	private TextField txtWeek2;

	@FXML
	private TextField txtWeek3;

	@FXML
	private TextField txtWeek4;

	@FXML
	private TextField txtWeek5;

	@FXML
	private TextField txtWeek6;

	@FXML
	private TextField txtLab1;

	@FXML
	private TextField txtLab2;

	@FXML
	private TextField txtMidtermExam;

	@FXML
	private TextField txtLab3;

	@FXML
	private TextField txtLab4;

	@FXML
	private TextField txtFinalExam;

	@FXML
	private TextField txtStudentUniqueId;

	@FXML
	private TextField txtViewStudentUniqueId;

	public void teacher() {
	}

	public void markStudentAttendance() {
		pane1.setVisible(true);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(false);
	}

	public void gradeStudent() {
		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
		pane4.setVisible(false);
	}

	public void viewStudentProfile() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);
		pane4.setVisible(false);
	}

	public void viewStudentGrades() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(true);
	}

// DB object
	DBConnect conn = null;
	Statement stmt = null;

	public TeacherController() {
		conn = new DBConnect();
	}

	public void submitAttendance() {

		try {

			lblStuAttError.setText("");
			;

			if ((txtAttFullName.getText() == null || txtAttFullName.getText().trim().equals(""))
					&& (txtWeek1.getText() == null || txtWeek1.getText().trim().equals(""))
					&& (txtWeek2.getText() == null || txtWeek2.getText().trim().equals(""))
					&& (txtWeek3.getText() == null || txtWeek1.getText().trim().equals(""))
					&& (txtWeek4.getText() == null || txtWeek4.getText().trim().equals(""))
					&& (txtWeek5.getText() == null || txtWeek5.getText().trim().equals(""))
					&& (txtWeek6.getText() == null || txtWeek6.getText().trim().equals(""))) {
				lblStuAttError.setText("At least one field should be enetred");
				return;
			}

			String fullName = null, week1 = null, week2 = null, week3 = null, week4 = null, week5 = null, week6 = null;

			// Execute a query
			System.out.println("Marking student's attendance...");

			fullName = txtAttFullName.getText();
			week1 = txtWeek1.getText();
			week2 = txtWeek2.getText();
			week3 = txtWeek3.getText();
			week4 = txtWeek4.getText();
			week5 = txtWeek5.getText();
			week6 = txtWeek6.getText();

			stmt = conn.getConnection().createStatement();
			String sql = null, dbUniqueId = null;
			ResultSet rs = null;

			sql = "select studuniqid, studfullname from stud_prof where studfullname = '" + fullName + "'";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dbUniqueId = rs.getString(1);
				// dbFullName = rs.getString(2);
			}

			String studentUniqueId = dbUniqueId;

			sql = "insert into stud_atten(studuniqid, studfullname, week1, week2, week3, week4, week5, week6) values ('"
					+ studentUniqueId + "','" + fullName + "','" + week1 + "','" + week2 + "','" + week3 + "','" + week4
					+ "','" + week5 + "','" + week6 + "')";

			stmt.executeUpdate(sql);

			System.out.println("Printing student's attendance info:");

			// System.out.println("studentUniqueId: " + studentUniqueId);
			System.out.println("fullName: " + fullName);
			System.out.println("Week1: " + week1);
			System.out.println("Week2: " + week2);
			System.out.println("Week3: " + week3);
			System.out.println("Week4: " + week4);
			System.out.println("Week5: " + week5);
			System.out.println("Week6: " + week6);

			System.out.println("Inserting new student into the Table.class..");
			System.out.println("Student Attendance created/updated");

			conn.getConnection().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submitGrades() {

		try {

			lblStuAttError.setText("");
			;

			if ((txtFullName.getText() == null || txtFullName.getText().trim().equals(""))
					&& (txtLab1.getText() == null || txtLab1.getText().trim().equals(""))
					&& (txtLab2.getText() == null || txtLab2.getText().trim().equals(""))
					&& (txtMidtermExam.getText() == null || txtMidtermExam.getText().trim().equals(""))
					&& (txtLab3.getText() == null || txtLab3.getText().trim().equals(""))
					&& (txtLab4.getText() == null || txtLab4.getText().trim().equals(""))
					&& (txtFinalExam.getText() == null || txtFinalExam.getText().trim().equals(""))) {
				lblStuGraError.setText("At least one field should be enetred");
				return;
			}

			String fullName = null, lab1 = null, lab2 = null, midtermEx = null, lab3 = null, lab4 = null,
					finalEx = null, finalScr = null, studentUniqueId;
			String dbFullName = null, dbUniqueId = null;
			double lab1Gr, lab2Gr, midtermExGr, lab3Gr, lab4Gr, finalExGr, finalScore;

			// Execute a query
			System.out.println("Grading student...");

			// String studentUniqueId = createId("SID",1000);
			fullName = txtFullName.getText();
			lab1 = txtLab1.getText();
			lab2 = txtLab2.getText();
			midtermEx = txtMidtermExam.getText();
			lab3 = txtLab3.getText();
			lab4 = txtLab4.getText();
			finalEx = txtFinalExam.getText();

			stmt = conn.getConnection().createStatement();
			String sql = null;
			ResultSet rs = null;

			sql = "select studuniqid, studfullname from stud_prof where studfullname = '" + fullName + "'";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dbUniqueId = rs.getString(1);
				dbFullName = rs.getString(2);
			}

			if (!fullName.equals(dbFullName)) {
				// clear label first
				lblStuGraError.setText("");
				lblStuGraError.setText("Student does not exist in database");
				conn.getConnection().close();
				return;
			}

			studentUniqueId = dbUniqueId;

			if (lab1 == null || lab1.equals("")) {
				lab1Gr = 0;
			} else {
				lab1Gr = Double.parseDouble(lab1);
			}
			if (lab2 == null || lab2.equals("")) {
				lab2Gr = 0;
			} else {
				lab2Gr = Double.parseDouble(lab2);
			}
			if (midtermEx == null || midtermEx.equals("")) {
				midtermExGr = 0;
			} else {
				midtermExGr = Double.parseDouble(midtermEx);
			}
			if (lab3 == null || lab3.equals("")) {
				lab3Gr = 0;
			} else {
				lab3Gr = Double.parseDouble(lab3);
			}
			if (lab4 == null || lab4.equals("")) {
				lab4Gr = 0;
			} else {
				lab4Gr = Double.parseDouble(lab4);
			}
			if (finalEx == null || finalEx.equals("")) {
				finalExGr = 0;
			} else {
				finalExGr = Double.parseDouble(finalEx);
			}

			double numericTotal;
			numericTotal = lab1Gr + lab2Gr + midtermExGr + lab3Gr + lab4Gr + finalExGr;
			if (numericTotal == 0) {
				finalScore = 0;
			} else {
				finalScore = numericTotal / 6;
			}

			if (finalScore >= 90) {
				finalScr = "A";
			} else if (finalScore >= 80) {
				finalScr = "B";
			} else if (finalScore >= 70) {
				finalScr = "C";
			} else if (finalScore >= 60) {
				finalScr = "D";
			} else {
				finalScr = "F";
			}

			System.out.println("Printing student's grades:");

			sql = "select studuniqid, studfullname from stud_grade where studfullname = '" + fullName + "'";

			rs = stmt.executeQuery(sql);

			// Clear first
			dbFullName = null;
			dbUniqueId = null;

			while (rs.next()) {
				dbUniqueId = rs.getString(1);
				dbFullName = rs.getString(2);
			}

			if (fullName.equals(dbFullName)) {
				sql = "update stud_grade set lab1 = '" + txtLab1.getText() + "', lab2 = '" + txtLab1.getText()
						+ "', midtermex = '" + txtMidtermExam.getText() + "', lab3 = '" + txtLab3.getText()
						+ "', lab4 = '" + txtLab1.getText() + "', finalex = '" + txtFinalExam.getText()
						+ "', finalgr = '" + finalScr + "' where studuniqid = '" + dbUniqueId + "'";
			} else {
				sql = "insert into stud_grade(studuniqid, studfullname, lab1, lab2, midtermex, lab3, lab4, finalex, finalgr) values ('"
						+ studentUniqueId + "','" + fullName + "','" + lab1 + "','" + lab2 + "','" + midtermEx + "','"
						+ lab3 + "','" + lab4 + "','" + finalEx + "','" + finalScr + "')";
			}

			stmt.executeUpdate(sql);

			System.out.println("studentUniqueId: " + studentUniqueId);
			System.out.println("fullName: " + fullName);
			System.out.println("Lab1: " + lab1);
			System.out.println("Lab2: " + lab2);
			System.out.println("Midterm Exam: " + midtermEx);
			System.out.println("Lab3: " + lab3);
			System.out.println("Lab4: " + lab4);
			System.out.println("Final Exam: " + finalEx);
			System.out.println("Final Score: " + finalScr);

			System.out.println("Submit Grade button pressed");
			System.out.println("Student Grade created");

			conn.getConnection().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewProfile() {

		try {

			lblViewError.setText("");
			lblViewProfFullName.setText("");
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
			String sql = null;
			String dbUniqueId = null;
			ResultSet rs = null;
			String fullName = null, dateOfBirth = null, gender = null, age = null, homeAddress = null,
					emergencyContact = null;

			sql = "select studuniqid, studfullname, studdob, studgender, studage, studaddr, studemer from stud_prof where studuniqid = '"
					+ studentUniqueId + "'";
			rs = stmt.executeQuery(sql);

			/*
			 * while (rs.next()) { dbUniqueId = rs.getString(1); }
			 * 
			 * if ( studentUniqueId.equals(dbUniqueId) ) { // clear label first
			 * lblStuGraError.setText("");
			 * lblStuGraError.setText("Student does not exist in database");
			 * conn.getConnection().close(); return; }
			 */

			// get row data from table!
			while (rs.next()) {
				dbUniqueId = rs.getString(1);
				fullName = rs.getString(2);
				dateOfBirth = rs.getString(3);
				gender = rs.getString(4);
				age = rs.getString(5);
				homeAddress = rs.getString(6);
				emergencyContact = rs.getString(7);
			}

			lblViewProfFullName.setText(fullName);
			lblViewDob.setText(dateOfBirth);
			lblViewGender.setText(gender);
			lblViewAge.setText(age);
			lblViewHomeAddr.setText(homeAddress);
			lblViewEmerCont.setText(emergencyContact);

			System.out.println("studentUniqueId: " + dbUniqueId);
			System.out.println("fullName: " + fullName);
			System.out.println("dateOfBirth: " + dateOfBirth);
			System.out.println("gender: " + gender);
			System.out.println("age: " + age);
			System.out.println("homeAddress: " + homeAddress);
			System.out.println("emergencyContact: " + emergencyContact);

			System.out.println("Student profile retrieved..");

			conn.getConnection().close();

		} catch (SQLException e) {
			System.out.println("AdminController::submit: Error occurred while creating student account: " + e);
			e.printStackTrace();
		}
	}

	public void viewGrades() {

		try {

			lblViewStuGraError.setText("");
			lblViewFullName.setText("");
			lblViewLab1Gr.setText("");
			lblViewLab2Gr.setText("");
			lblViewMidtermExGr.setText("");
			lblViewLab3Gr.setText("");
			lblViewLab4Gr.setText("");
			lblViewFinalExGr.setText("");
			;
			lblViewFinalScore.setText("");
			;

			if ((txtStudentUniqueId.getText() == null || txtStudentUniqueId.getText().trim().equals(""))) {
				lblViewStuGraError.setText("The Student Unique ID field cannot be empty or spaces");
				return;
			}

			String studentUniqueId = txtStudentUniqueId.getText();
			stmt = conn.getConnection().createStatement();
			String sql = null;
			ResultSet rs = null;

			sql = "select studuniqid, studfullname, lab1, lab2, midtermex, lab3, lab4, finalex, finalgr from stud_grade where studuniqid = '"
					+ studentUniqueId + "'";
			rs = stmt.executeQuery(sql);

			/*
			 * while (rs.next()) { dbUniqueId = rs.getString(1); }
			 * 
			 * if ( studentUniqueId.equals(dbUniqueId) ) { // clear label first
			 * lblViewStuGraError.setText("");
			 * lblViewStuGraError.setText("Student does not exist in database");
			 * conn.getConnection().close(); return; }
			 */
			String fullName = null, lab1 = null, lab2 = null, midEx = null, lab3 = null, lab4 = null, finEx = null,
					finGr = null;

			// get row data from table!
			while (rs.next()) {
				fullName = rs.getString(2);
				lab1 = rs.getString(3);
				lab2 = rs.getString(4);
				midEx = rs.getString(5);
				lab3 = rs.getString(6);
				lab4 = rs.getString(7);
				finEx = rs.getString(8);
				finGr = rs.getString(9);
			}

			lblViewFullName.setText(fullName);
			lblViewLab1Gr.setText(lab1);
			lblViewLab2Gr.setText(lab2);
			lblViewMidtermExGr.setText(midEx);
			lblViewLab3Gr.setText(lab3);
			lblViewLab4Gr.setText(lab4);
			lblViewFinalExGr.setText(finEx);
			lblViewFinalScore.setText(finGr);

			System.out.println("studentUniqueId: " + studentUniqueId);
			System.out.println("fullName: " + fullName);
			System.out.println("Lab 1: " + lab1);
			System.out.println("Lab 2: " + lab2);
			System.out.println("Midterm Exam " + midEx);
			System.out.println("Lab 3: " + lab3);
			System.out.println("Lab 4: " + lab4);
			System.out.println("Final Exam: " + finEx);
			System.out.println("Final Score: " + finGr);

			System.out.println("Student's grade has been retrieved...");

			conn.getConnection().close();

		} catch (SQLException e) {
			System.out.println("AdminController::viewGrades: Error occurred while viewing student's grades: " + e);
			e.printStackTrace();
		}
	} // end viewGrades

}
