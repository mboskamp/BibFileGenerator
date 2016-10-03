package control.viewController;

import java.awt.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class AddController {

	@FXML
	ChoiceBox<String> selectType;
	
	@FXML
	public void initialize(){
		
	}
	
	public void ok(){
		System.out.println("OK");
	}
	
	public void cancel(){
		System.out.println("cancel");
	}
}