package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class AbstractPrintEntryController extends AbstractEntryController {

	@FXML
	public TextField author;
	
	@Override
	public abstract void initialize();

}
