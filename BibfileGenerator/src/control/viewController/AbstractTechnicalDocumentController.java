package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class AbstractTechnicalDocumentController extends AbstractPrintEntryController {

	@FXML
	public TextField address;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}
}
