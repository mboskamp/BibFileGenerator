package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_PROCEEDINGS proceedings} entry.
 * 
 * @author Miklas Boskamp
 */
public class ProceedingsController extends AbstractEntryController {

	@FXML
	public EntryTextField editor;
	
	/**
	 * volume/number
	 */
	@FXML
	public EntryTextField volume;
	
	/**
	 * volume/number
	 */
	@FXML
	public EntryTextField number;
	
	@FXML
	public EntryTextField series;
	
	@FXML
	public EntryTextField address;
	
	@FXML
	public EntryTextField publisher;
	
	@FXML
	public EntryTextField organization;
	
	@Override
	public void initialize() {
		// Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_PROCEEDINGS;
	}
}