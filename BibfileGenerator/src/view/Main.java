package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Entry point of this application. Contains the {@link #main(String[])} method.
 * Defines the visual parts of the applications.
 * 
 * @author Miklas Boskamp
 */
public class Main extends Application {

	@Override
	/**
	 * Initialization of all the visible parts of the application.
	 */
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/MainWindow.fxml"));
			Scene scene = new Scene(root, 700, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Entry point of this application. Before launching the application, this
	 * method checks if the runtime device has Internet access.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}