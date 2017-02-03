package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about an {@link BibTeXEntry#TYPE_INCOLLECTION incollection}.
 * 
 * @author Miklas Boskamp
 */
public class IncollectionController extends AbstractISBNController {

	@FXML
	public EntryTextField booktitle;
	
	@FXML
	public EntryTextField type;
	
	@FXML
	public EntryTextField chapter;
	
	@FXML
	public EntryTextField pages;
	
	@Override
	public void initialize() {
		// Do nothing
		super.initialize();
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_INCOLLECTION;
	}
}