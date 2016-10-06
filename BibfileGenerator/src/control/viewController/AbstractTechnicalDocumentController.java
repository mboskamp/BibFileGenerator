package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class AbstractTechnicalDocumentController extends AbstractPrintEntryController {

	@FXML
	public TextField address;
	
	@Override
	public abstract void initialize();
}
