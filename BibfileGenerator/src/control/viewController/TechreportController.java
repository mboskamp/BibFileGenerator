package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_TECHREPORT techreport}.
 * 
 * @author Miklas Boskamp
 */
public class TechreportController extends AbstractTechnicalDocumentController {

	@FXML
	public EntryTextField institution;
	
	@FXML
	public EntryTextField type;
	
	@FXML
	public EntryTextField number;
	
	@Override
	public void initialize() {
		// Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_TECHREPORT;
	}
}
