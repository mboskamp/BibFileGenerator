package control.viewController;


import java.io.IOException;
import java.util.ArrayList;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Entry;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GlobalStorage;

public class MainWindowController extends AbstractController{
	
	private ArrayList<Entry> entries = new ArrayList<>();
	
	@FXML
	public TableView<Entry> table;
	
	//Test
	@FXML
    public void initialize() {
		
		for (String s : BibTeXEntry.getKeysAsString()) {
			TableColumn<Entry, String> column = new TableColumn<>();
			column.setText(s);
			column.setCellValueFactory(
					new PropertyValueFactory<Entry, String>(s));
			
			table.getColumns().add(column);			
		}
    }
	
	public void add(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NewEntryWindow.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			
			AbstractController c = fxmlLoader.getController();
			c.setFrom(this);
			
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
	
	public void notifyAdd(String key){
		BibTeXEntry entry = (BibTeXEntry) GlobalStorage.getInstance().get(key);
		this.entries.add(entry.getEntry());
		
		ObservableList<Entry> entries =  FXCollections.observableArrayList(this.entries);
		table.setItems(entries);
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