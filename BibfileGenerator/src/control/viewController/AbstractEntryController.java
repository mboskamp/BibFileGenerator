package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.GlobalStorage;

/**
 * Abstract superclass for any view controller that is used to gather
 * information about a new entry in the Bibfile.<br>
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractEntryController extends AbstractController {

	@FXML
	public TextField title;

	@FXML
	public TextField year;

	@FXML
	public TextField month;

	@FXML
	public TextField note;

	@FXML
	public TextField key;

	@FXML
	public TextField referenceKey;

	@FXML
	public abstract void initialize();

	// @FXML
	// public abstract void finalize();

	/**
	 * Called when the user hits the 'add' button in the NewEntryView.
	 * @return The reference key to retrieve the saved entry from {@link GlobalStorage}.
	 */
	public abstract String saveData();
}