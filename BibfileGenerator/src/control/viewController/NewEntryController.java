package control.viewController;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.stage.Stage;

public class NewEntryController {

	@FXML
	Button cancelButton;
	
	@FXML
	ChoiceBox<Object> entryType;
	
	@FXML
	public void initialize(){
		entryType.setItems(FXCollections.observableArrayList(
		    "New Document", "Open ", 
		    new Separator(), "Save", "Save as")
		);
	}
	
	public void ok(){
		System.out.println("OK");
	}
	
	@FXML
	public void cancel(){
		// get a handle to the stage
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
}