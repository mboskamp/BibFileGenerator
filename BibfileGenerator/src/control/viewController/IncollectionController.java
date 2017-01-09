package control.viewController;

import org.jbibtex.BibTeXEntry;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about an {@link BibTeXEntry#TYPE_INCOLLECTION incollection}.
 * 
 * @author Miklas Boskamp
 */
public class IncollectionController extends AbstractISBNController {

	@FXML
	public TextField booktitle;
	
	@FXML
	public TextField type;
	
	@FXML
	public TextField chapter;
	
	@FXML
	public TextField pages;
	
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