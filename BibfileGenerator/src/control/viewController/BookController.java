package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	public TextField author;
	
	/**
	 * Author/Editor
	 */
	@FXML
	public TextField editor;
	
	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_BOOK;
	}
}