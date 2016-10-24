package control.viewController;

import org.jbibtex.BibTeXEntry;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_BOOKLET booklet}.
 * 
 * @author Miklas Boskamp
 */
public class BookletController extends AbstractPrintEntryController {

	@FXML
	public TextField howpublished;
	
	@FXML
	public TextField address;
	
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
