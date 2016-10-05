package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProceedingsController extends AbstractEntryController {

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
	public TextField address;
	
	@FXML
	public TextField publisher;
	
	@FXML
	public TextField organization;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}
}