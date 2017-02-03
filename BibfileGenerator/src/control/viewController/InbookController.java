package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about an {@link BibTeXEntry#TYPE_INBOOK inbook}.
 * 
 * @author Miklas Boskamp
 */
public class InbookController extends AbstractISBNController {

	/**
	 * Author/Editor
	 */
	@FXML
	public EntryTextField author;
	
	/**
	 * Author/Editor
	 */
	@FXML
	public EntryTextField editor;

	/**
	 * chapter/pages
	 */
	@FXML
	public EntryTextField chapter;
	
	/**
	 * chapter/pages
	 */
	@FXML
	public EntryTextField pages;
	
	@FXML
	public EntryTextField type;
	
	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_INBOOK;
	}
}
