package controllers;

/**
 * This StudentController class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

public class StudentController {

	@FXML
	private Pane pane1;

	@FXML
	private Pane pane2;

	@FXML
	private Pane pane3;

	@FXML
	private Pane pane4;

	@FXML
	private Pane pane5;

	@FXML
	private Label lblUpdError;

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

	public void studentone() {
	}

	public void updateOwnProfile() {
		pane1.setVisible(true);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(false);
		pane5.setVisible(false);
	}

	public void viewGrades() {
		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
		pane4.setVisible(false);
		pane5.setVisible(false);
	}

	public void viewHomework() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);
		pane4.setVisible(false);
		pane5.setVisible(false);
	}

	public void viewPaymentBalance() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(true);
		pane5.setVisible(false);
	}

	public void viewSchedule() {
		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(false);
		pane4.setVisible(false);
		pane5.setVisible(true);
	}

	public void update() {

		lblUpdError.setText("");

		if ((txtFullName.getText() == null || txtFullName.getText().trim().equals(""))
				|| (txtDateOfBirth.getText() == null || txtDateOfBirth.getText().trim().equals(""))
				|| (txtGender.getText() == null || txtGender.getText().trim().equals(""))
				|| (txtAge.getText() == null || txtAge.getText().trim().equals(""))
				|| (txtHomeAddress.getText() == null || txtHomeAddress.getText().trim().equals(""))
				|| (txtEmergencyContact.getText() == null || txtEmergencyContact.getText().trim().equals(""))) {
			lblUpdError.setText("The fields cannot be empty or spaces");
		}

		System.out.println("Update button pressed");
	}

}
