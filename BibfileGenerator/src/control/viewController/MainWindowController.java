package control.viewController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXFormatter;
import org.jbibtex.BibTeXParser;
import org.jbibtex.Entry;
import org.jbibtex.Key;
import org.jbibtex.ObjectResolutionException;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import model.GlobalStorage;

public class MainWindowController extends AbstractController {
	
	private String path;
	
	private ArrayList<Entry> entries = new ArrayList<>();
	private ArrayList<TableColumn<Entry, String>> columns = new ArrayList<>();
	private BibTeXDatabase db = new BibTeXDatabase();

	@FXML
	public TableView<Entry> table;

	@FXML
	public void initialize() {

		for (String s : BibTeXEntry.getKeysAsString()) {
			TableColumn<Entry, String> column = new TableColumn<Entry, String>();
			column.setText(s);
			column.setCellValueFactory(new PropertyValueFactory<Entry, String>(s));
			table.getColumns().add(column);
		}
	}

	public void add() {
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

	public void notifyAdd(String key) {
		BibTeXEntry entry = (BibTeXEntry) GlobalStorage.getInstance().get(key);
		db.addObject(entry);
		Entry e = entry.getEntry();
		this.entries.add(e);

		updateColumns();

		updateTable();
	}

	public void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open...");
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BibFiles (*.bib)", "*.bib");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showOpenDialog(table.getScene().getWindow());
		this.path = file.getAbsolutePath();
		
		BibTeXParser parser;
		try {
			parser = new BibTeXParser();
			db = parser.parse(new FileReader(new File(path)));
			
			for (Map.Entry<Key, BibTeXEntry> entry : db.getEntries().entrySet()) {
				this.entries.add(entry.getValue().getEntry());
			}
			
			updateColumns();
			updateTable();
		} catch (TokenMgrException | ParseException | ObjectResolutionException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save() {
		System.out.println(path == null ? "null" : path);
		if(path != null){
			BibTeXFormatter formatter = new BibTeXFormatter();
			try {
				formatter.format(db, new FileWriter(new File(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			saveAs();
		}
	}

	public void saveAs() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save as...");
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BibFiles (*.bib)", "*.bib");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showSaveDialog(table.getScene().getWindow());
		this.path = file.getAbsolutePath();
		save();
	}

	public void merge() {
		System.out.println("Merge");
	}

	private void updateColumns() {
		// table.getColumns().clear();
		// for (Entry entry : entries) {
		// if(entry != null){
		// for (Value value : entry.getValues()) {
		// if(value != null){
		// TableColumn<Entry, String> column = new TableColumn<Entry, String>();
		// column.setText(entry.);
		// column.setCellValueFactory(
		// new PropertyValueFactory<Entry,String>(value.toUserString())
		// );
		// table.getColumns().add(column);
		// }
		// }
		//
		// }
		// }
	}

	private void updateTable() {
		ObservableList<Entry> entries = FXCollections.observableArrayList(this.entries);
		table.setItems(entries);
	}
}