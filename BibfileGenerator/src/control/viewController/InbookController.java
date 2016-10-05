package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InbookController extends AbstractISBNController {

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

	/**
	 * chapter/pages
	 */
	@FXML
	public TextField chapter;
	
	/**
	 * chapter/pages
	 */
	@FXML
	public TextField pages;
	
	@FXML
	public TextField type;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}
}
