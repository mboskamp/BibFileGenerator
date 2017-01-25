package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_PROCEEDINGS proceedings} entry.
 * 
 * @author Miklas Boskamp
 */
public class ProceedingsController extends AbstractEntryController {

	@FXML
	public TextField editor;
	
	/**
	 * volume/number
	 */
	@FXML
	public TextField volume;
	
	/**
	 * volume/number
	 */
	@FXML
	public TextField number;
	
	@FXML
	public TextField series;
	
	@FXML
	public TextField address;
	
	@FXML
	public TextField publisher;
	
	@FXML
	public TextField organization;
	
	@Override
	public void initialize() {
		// Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_PROCEEDINGS;
	}
}