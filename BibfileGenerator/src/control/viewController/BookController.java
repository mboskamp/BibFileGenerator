package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookController extends AbstractISBNController {

	
	/**
	 * Author/Editor
	 */
	@FXML
	public TextField author;
	
	/**
	 * Author/Editor
	 */
	@FXML
	public TextField editor;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}
}