package control.viewController;

import org.jbibtex.BibTeXEntry;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about an {@link BibTeXEntry#TYPE_ARTICLE article}.
 * 
 * @author Miklas Boskamp
 */
public class ArticleController extends AbstractPrintEntryController {

	@FXML
	public TextField journal;
	
	@FXML
	public TextField volume;
	
	@FXML
	public TextField number;
	
	@FXML
	public TextField pages;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public String saveData() {
		// TODO Auto-generated method stub
		return null;
	}
}