package control.viewController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXFormatter;
import org.jbibtex.BibTeXObject;
import org.jbibtex.BibTeXParser;
import org.jbibtex.Entry;
import org.jbibtex.ParseException;
import org.jbibtex.StringValue;
import org.jbibtex.TokenMgrException;
import org.jbibtex.Value;

import control.error.Error;
import control.error.ExceptionDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller that handles the main view window.
 * 
 * @author Miklas Boskamp
 */
public class MainWindowController extends AbstractController {

	private AbstractEntryController contentController;
	private String path;

	private ArrayList<BibTeXEntry> entries = new ArrayList<>();
	private BibTeXDatabase db = new BibTeXDatabase();

	@FXML
	public TableView<Entry> table;

	@FXML
	public Button removeBtn;
	
	@FXML
	public ScrollPane contentWrapper;

	@FXML
	public MenuItem removeMenu;

	/**
	 * Called when view is initialized.
	 */
	@FXML
	public void initialize() {
		int i = 0;
		for (String s : BibTeXEntry.getKeysAsString()) {
			TableColumn<Entry, String> column = new TableColumn<Entry, String>();
			column.setText(s);
			column.setCellValueFactory(new PropertyValueFactory<Entry, String>(s));
			table.getColumns().add(column);
			// damit alle Spalten angezeigt werden, dieses IF auskommentieren
			// 2 = Autor, 5 = Referenz, 21 = Titel, 25 = Jahr
			if (!(i == 2 || i == 5 || i == 21 || i == 25)) {
				table.getColumns().get(i).setVisible(false);
			}
			i++;
		}

		table.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null && newSelection.intValue() != -1) {
				System.out.println("New Selection: " + newSelection.intValue());
				if(oldSelection.intValue() != -1){
					saveDetailView(newSelection.intValue(), oldSelection.intValue());
				}
				updateDetailView(newSelection.intValue());
			}
			removeBtn.setDisable(false);
			removeMenu.setDisable(false);
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
			stage.setTitle("BibFileGenerator - Neuen Eintrag hinzufügen");
			stage.getIcons().add(new Image(getClass().getResourceAsStream(view.Main.getAPPLICATION_ICON_PATH())));
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
		if (table.getSelectionModel().getSelectedItem() == null) {
			removeBtn.setDisable(true);
			removeMenu.setDisable(true);
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
		//db.addObject(entry);
//		entry.setType(new StringValue(entry.getType().toString()));
		this.entries.add(entry);

		updateTable();
	}

	/**
	 * TODO Doesn't work
	 * Opens a new 'open' dialog where the user can choose to open an existing
	 * .bib file. This file is then loaded, parsed and the containing entries
	 * are displayed in the list.
	 */
	public void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Öffnen");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BibFiles (*.bib)", "*.bib");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(table.getScene().getWindow());
		try {
			this.path = file.getAbsolutePath();
		} catch (NullPointerException e) {
			System.out.println("Beim Öffnen wurde abbrechen geddrückt");
			return;
		}

		BibTeXParser parser;
		try {
			parser = new BibTeXParser();
			db = parser.parse(new FileReader(new File(path)));

      this.entries.clear();
			for (BibTeXObject entry : db.getObjects()) {
				this.entries.add(((BibTeXEntry)entry));

			}

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
		db = new BibTeXDatabase();
		System.out.println(path == null ? "null" : path);
		if (path != null) {
			BibTeXFormatter formatter = new BibTeXFormatter();
			try {
				for (BibTeXEntry entry : entries) {
					db.addObject(entry);
				}
				
				
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
		fileChooser.setTitle("Speichern als...");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BibFiles (*.bib)", "*.bib");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(table.getScene().getWindow());
		try {
			this.path = file.getAbsolutePath();
		} catch (NullPointerException e) {
			System.out.println("Beim Speichern wurde abbrechen geddrückt");
			return;
		}
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

	/**
	 * Updates the table of entries.
	 */
	private void updateTable() {
		ArrayList<Entry> entryList = new ArrayList<>();
		for (BibTeXEntry bibTeXEntry : entries) {
			entryList.add(bibTeXEntry.getEntry());
		}
		ObservableList<Entry> entries = FXCollections.observableArrayList(entryList);
		table.setItems(entries);
	}
	
	private void saveDetailView(int index, int oldIndex){
		if(contentController != null){
			entries.set(oldIndex, contentController.saveData());
		}
	}
	
	private void updateDetailView(int index){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/entries/detail/" + entries.get(index).getType() + ".fxml"));
			contentWrapper.setContent((Node) loader.load());
			contentController = loader.getController();
			contentController.setFrom(this);
			
			BibTeXEntry entry = contentController.updateBibTeXEntry(entries.get(index), index);
			if(entry != null){
				entries.set(index, entry);
			}
			updateTable();

		} catch (IllegalStateException ise){
		} catch (IOException e) {
			new ExceptionDialog(Error.VIEW_LOAD_ERROR, e);
		}
	}
}