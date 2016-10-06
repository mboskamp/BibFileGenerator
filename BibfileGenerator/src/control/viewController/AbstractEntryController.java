package control.viewController;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public abstract class AbstractEntryController {

	@FXML
	public TextField title;
	
	@FXML
	public TextField year;
	
	@FXML 
	public TextField month;
	
	@FXML
	public TextField note;
	
	@FXML
	public TextField key;
	
	@FXML
	public TextField referenceKey;

	@FXML
	public abstract void initialize();

	// @FXML
	// public abstract void finalize();
	
	public abstract void saveData();
}