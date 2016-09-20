package control.viewController;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Button createButton;
	
	@FXML
	private Button loadButton;
	
	//Test
	@FXML
	public void load(){
		System.out.println("Load");
		
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 File selectedFile = fileChooser.showOpenDialog(new Stage());
		 fileChooser.getExtensionFilters().add(new ExtensionFilter("Bibtex Files", "*.bib"));
		 if(selectedFile != null && selectedFile.getAbsolutePath().endsWith(".bib")){
			 System.out.println("File selected");
			 //TODO Read file
		 }else{
			 System.out.println("wrong extension");
		 }
	}
	
	@FXML
	public void create(){
		System.out.println("Create new");
		
		Stage stage = (Stage) createButton.getScene().getWindow();
	}
	
	//Test
	@FXML
    public void initialize() {
		
    }
	
}