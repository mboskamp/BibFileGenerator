package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_MISC misc} entry.
 * 
 * @author Miklas Boskamp
 */
public class MiscController extends AbstractPrintEntryController {

	@FXML
	public TextField howpublished;
	
	@Override
	public void initialize() {
		// Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_MISC;
	}
}
