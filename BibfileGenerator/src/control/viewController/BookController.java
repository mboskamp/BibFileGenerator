package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_BOOK book}.
 * 
 * @author Miklas Boskamp
 */
public class BookController extends AbstractISBNController {
	
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
	
	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_BOOK;
	}
}