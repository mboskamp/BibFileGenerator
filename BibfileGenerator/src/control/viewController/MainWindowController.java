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
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import control.error.Error;
import control.error.ExceptionDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

/**
 * Controller that handles the main view window.
 * 
 * @author Miklas Boskamp
 */
public class MainWindowController extends AbstractController {

	private String path;

	private ArrayList<Entry> entries = new ArrayList<>();
	private BibTeXDatabase db = new BibTeXDatabase();

	@FXML
	public TableView<Entry> table;

	@FXML
	public Button removeBtn;

	/**
	 * Called when view is initialized.
	 */
	@FXML
	public void initialize() {
		for (String s : BibTeXEntry.getKeysAsString()) {
			TableColumn<Entry, String> column = new TableColumn<Entry, String>();
			column.setText(s);
			column.setCellValueFactory(new PropertyValueFactory<Entry, String>(s));
			table.getColumns().add(column);
		}

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				System.out.println("New Selection: " + newSelection.getTitle());
			}
			removeBtn.setDisable(false);
		});
	}

	/**
	 * Opens the NewEntryWindow where the user can insert a new Entry.
	 */
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

	public void remove() {
		System.out.println("Removed: " + table.getSelectionModel().getSelectedItem().getTitle() + " at index "
				+ table.getSelectionModel().selectedIndexProperty().intValue());
		entries.remove(table.getSelectionModel().selectedIndexProperty().intValue());
		updateTable();
		if (table.getSelectionModel().getSelectedItem() == null){
			removeBtn.setDisable(true);
		}
	}

	/**
	 * Is called by the {@link NewEntryController}. If called new data is stored
	 * in {@link GlobalStorage} and can now be accessed via the given string
	 * key.<br>
	 * The stored entry is then retrieved and is added to the list of entries.
	 * 
	 * @param key
	 */
	public void notifyAdd(BibTeXEntry entry) {
		db.addObject(entry);
		Entry e = entry.getEntry();
		this.entries.add(e);

		updateTable();
	}

	/**
	 * Opens a new 'open' dialog where the user can choose to open an existing
	 * .bib file. This file is then loaded, parsed and the containing entries
	 * are displayed in the list.
	 */
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
		} catch (TokenMgrException | ParseException e) {
			new ExceptionDialog(Error.INTERNAL_ERROR, e);
		} catch (FileNotFoundException e) {
			new ExceptionDialog(Error.FILE_NOT_FOUND_ERROR, e);
		}
	}

	/**
	 * The 'save' option calls this method that opens a new 'save' dialog. If no
	 * current save path is present (e.g. if this is the first time this file is
	 * saved) the {@link #saveAs()} method is called.
	 */
	public void save() {
		System.out.println(path == null ? "null" : path);
		if (path != null) {
			BibTeXFormatter formatter = new BibTeXFormatter();
			try {
				formatter.format(db, new FileWriter(new File(path)));
			} catch (IOException e) {
				new ExceptionDialog(Error.FORMATTING_ERROR, e);
			}
		} else {
			saveAs();
		}
	}

	/**
	 * The 'save as...' option calls this method that opens a new 'save as'
	 * dialog where the user can specify a path where the file should be saved.
	 * The current save path will be updated with this path.
	 */
	public void saveAs() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save as...");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BibFiles (*.bib)", "*.bib");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(table.getScene().getWindow());
		this.path = file.getAbsolutePath();
		if (path != null) {
			save();
		}
	}

	/**
	 * EXPERIMENTAL This method is not yet implemented.
	 */
	public void merge() {
		System.out.println("Merge");
	}

	private void updateColumns() {
	}

	/**
	 * Updates the table of entries.
	 */
	private void updateTable() {
		ObservableList<Entry> entries = FXCollections.observableArrayList(this.entries);
		table.setItems(entries);
	}
}