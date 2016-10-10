package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class AbstractEntryController extends AbstractController{
	
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
	
	public abstract String saveData();
}