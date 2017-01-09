package control.viewController;

import org.jbibtex.BibTeXEntry;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	public TextField author;
	
	/**
	 * Author/Editor
	 */
	@FXML
	public TextField editor;

	/**
	 * chapter/pages
	 */
	@FXML
	public TextField chapter;
	
	/**
	 * chapter/pages
	 */
	@FXML
	public TextField pages;
	
	@FXML
	public TextField type;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	public BibTeXEntry saveData() {
		// TODO Auto-generated method stub
		return null;
	}
}
