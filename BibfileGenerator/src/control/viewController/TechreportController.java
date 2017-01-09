package control.viewController;

import org.jbibtex.BibTeXEntry;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_TECHREPORT techreport}.
 * 
 * @author Miklas Boskamp
 */
public class TechreportController extends AbstractTechnicalDocumentController {

	@FXML
	public TextField institution;
	
	@FXML
	public TextField type;
	
	@FXML
	public TextField number;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public BibTeXEntry saveData() {
		// TODO Auto-generated method stub
		return null;
	}
}
