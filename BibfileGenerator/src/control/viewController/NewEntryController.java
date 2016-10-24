package control.viewController;

import java.io.IOException;
import java.util.ArrayList;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Entry;

import control.viewController.MainWindowController;

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
import model.GlobalStorage;

/**
 * Controller that handles the input of user data about a new entry of any
 * type.<br>
 * The corresponding view will load the right {@link AbstractEntryController}
 * that will manage the data input.
 * 
 * @author Miklas Boskamp
 */
public class NewEntryController extends AbstractController {

	private AbstractEntryController contentController;

	@FXML
	private Button cancelButton;

	@FXML
	private Button addButton;

	@FXML
	private ChoiceBox<Object> entryType;

	@FXML
	private BorderPane contentWrapper;

	private ArrayList<String> types;

	/**
	 * Called when view is initialized.
	 */
	@FXML
	public void initialize() {
		types = BibTeXEntry.getTypesAsString();
		entryType.setItems(FXCollections.observableArrayList(types));
		entryType.getSelectionModel().selectFirst();

		changeContent(entryType.getSelectionModel().getSelectedIndex());

		entryType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("changed from " + types.get(oldValue.intValue()) + "(" + oldValue.intValue() + ")"
						+ " to " + types.get(newValue.intValue()) + "(" + newValue.intValue() + ")");
				changeContent(newValue.intValue());
			}
		});
	}

	/**
	 * Loads the right view for the data input depending on the selected entry
	 * type value.
	 * 
	 * @see NewEntryController#entryType
	 * @param index
	 *            Index of the selection.
	 */
	private void changeContent(int index) {
		String entry = BibTeXEntry.getTypes().get(index).getValue();
		try {
			contentWrapper.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/entries/" + entry + ".fxml"));
			contentWrapper.getChildren().add((Node) loader.load());

			contentController = loader.getController();

		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}

	/**
	 * Notifies the current {@link AbstractEntryController} to save the current
	 * input to {@link GlobalStorage}. Closes the NewEntryWindow and displays
	 * the MainView again. The MainView is notified that new data was stored and
	 * is now available.
	 */
	@FXML
	public void add() {
		String key = contentController.saveData();

		((MainWindowController) from).notifyAdd(key);

		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * Closes the NewEntryWindow and displays the MainView again.
	 */
	@FXML
	public void cancel() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}