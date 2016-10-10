package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ArticleController extends AbstractPrintEntryController {

	@FXML
	public TextField journal;
	
	@FXML
	public TextField volume;
	
	@FXML
	public TextField number;
	
	@FXML
	public TextField pages;
	
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