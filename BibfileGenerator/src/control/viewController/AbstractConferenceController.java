package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class AbstractConferenceController extends AbstractPrintEntryController {

	@FXML
	public TextField booktitle;
	
	@FXML
	public TextField editor;
	
	/**
	 * volume/number
	 */
	@FXML
	public TextField volume;
	
	/**
	 * volume/number
	 */
	@FXML
	public TextField number;
	
	@FXML
	public TextField series;
	
	@FXML
	public TextField pages;
	
	@FXML
	public TextField address;
	
	@FXML
	public TextField organization;
	
	@FXML
	public TextField publisher;
	
	@Override
	public abstract void initialize();
}
