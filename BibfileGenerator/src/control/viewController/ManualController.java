package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_MANUAL manual}.
 * 
 * @author Miklas Boskamp
 */
public class ManualController extends AbstractTechnicalDocumentController {

	@FXML
	public TextField organization;
	
	@FXML
	public TextField edition;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_MANUAL;
	}
}
