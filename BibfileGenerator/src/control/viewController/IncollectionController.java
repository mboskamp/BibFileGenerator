package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	public void saveData() {
		// TODO Auto-generated method stub
		
	}
}