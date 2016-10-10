package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	public String saveData() {
		// TODO Auto-generated method stub
		return null;
	}
}
