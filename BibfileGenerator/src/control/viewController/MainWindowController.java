package control.viewController;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController {
	
	//Test
	@FXML
    public void initialize() {
		
    }
	
	public void add(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NewEntryWindow.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Add New Entry");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void open(){
		
	}
	
	public void save(){
		
	}
	
	public void saveAs(){
		
	}
	
	public void merge(){
		
	}
	
}