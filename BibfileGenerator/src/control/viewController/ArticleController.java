package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about an
 * {@link BibTeXEntry#TYPE_ARTICLE article}.
 * 
 * @author Miklas Boskamp
 */
public class ArticleController extends AbstractPrintEntryController {

	@FXML
	public EntryTextField journal;

	@FXML
	public EntryTextField volume;

	@FXML
	public EntryTextField number;

	@FXML
	public EntryTextField pages;

	@Override
	public void initialize() {
		//Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_ARTICLE;
	}
}