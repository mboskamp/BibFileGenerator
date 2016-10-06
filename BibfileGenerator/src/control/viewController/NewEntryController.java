package control.viewController;


import java.io.IOException;
import java.util.ArrayList;

import org.jbibtex.BibTeXEntry;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewEntryController {

	private AbstractEntryController contentController;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private ChoiceBox<Object> entryType;
	
	@FXML
	private BorderPane contentWrapper;
	
	private ArrayList<String> types;
		
	@FXML
	public void initialize(){
		types = BibTeXEntry.getTypesAsString();
		entryType.setItems(FXCollections.observableArrayList(types));
		entryType.getSelectionModel().selectFirst();
		
		changeContent(entryType.getSelectionModel().getSelectedIndex());
		
		entryType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("changed from " + types.get(oldValue.intValue()) + "(" + oldValue.intValue() +  ")" + " to " + types.get(newValue.intValue()) + "(" + newValue.intValue() + ")");
				changeContent(newValue.intValue());
			}
		});
	}
	
	private void changeContent(int index){
		String entry = BibTeXEntry.getTypes().get(index).getValue();
		try {
			contentWrapper.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/entries/" + entry + ".fxml"));
			contentWrapper.getChildren().add((Node) loader.load());
			
			contentController = loader.getController();
			
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		}
	}
	
	@FXML
	public void add(){
		System.out.println("OK");
		//cancel(); //TODO remove and implement correct functionality
	}
	
	@FXML
	public void cancel(){
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
}