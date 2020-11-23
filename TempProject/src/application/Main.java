package application;

/**
 * This Main class 
 */

import javafx.application.Application; // To access Application class
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage; // To access Stage class
import javafx.scene.Scene; // To access Scene class
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	public static Stage stage; // set global stage object!!!

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Call Stage's object setTitle method to display on window's title bar
			stage.setTitle("Login View");
			stage.setScene(scene);
			// Call Stage's object "show" method to display application window
			stage.show();

		} catch (Exception e) {
			System.out.println("Error occurred while inflating login view: " + e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Call launch method to create Stage object and call "start" method
		launch(args);
	}

}
