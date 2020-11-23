package controllers;

/**
 * This LoginController class
 */

//import javafx.application.Application;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import application.Main;
import models.LoginModel;

public class LoginController {

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label lblError;

	private LoginModel model;

	public LoginController() {
		model = new LoginModel();
	}

	public void login() {

		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();

		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		} else {
			lblError.setText("Login or Password incorrect!");
		}

		System.out.println("Calling checkCredentials");

		// authentication check
		checkCredentials(username, password);

	} // End login() method

	public void checkCredentials(String username, String password) {

		System.out.println("LoginController.java::checkCredentials: checkCredentials called");

		Boolean isValid = model.getCredentials(username, password);
		if (!isValid) {
			lblError.setText("User does not exist!");
			return;
		}
		try {
			AnchorPane root;
			if (username.equals("admin810")) {

				// If user is admin810, inflate admin view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
				Main.stage.setTitle("Admin View");
				Scene scene = new Scene(root);
				Main.stage.setScene(scene);

			} else if (username.equals("teacher810")) {
				// If user is teacher810, inflate teacher view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/TeacherView.fxml"));
				Main.stage.setTitle("Teacher View");
				Scene scene = new Scene(root);
				Main.stage.setScene(scene);
			} else if (username.equals("student810")) {
				// If user is studentone810, inflate student view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StudentView.fxml"));
				Main.stage.setTitle("Student View");
				Scene scene = new Scene(root);
				Main.stage.setScene(scene);
			}

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}
	}

}
