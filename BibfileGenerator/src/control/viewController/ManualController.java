package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_MANUAL manual}.
 * 
 * @author Miklas Boskamp
 */
public class ManualController extends AbstractTechnicalDocumentController {

	@FXML
	public EntryTextField organization;
	
	@FXML
	public EntryTextField edition;
	
	@Override
	public void initialize() {
		// Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_MANUAL;
	}
}
