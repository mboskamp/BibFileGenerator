package control.viewController;

import control.isbn.ISBNValidator;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML
	private TextField isbn;
	
	@FXML
	private ProgressBar progress;
	
	@FXML
	public void pressButton(){
		System.out.println("pressed");
		progress.setProgress(0.0);
		String input = isbn.getText();
		boolean valid = ISBNValidator.validateISBN(input);
		System.out.println(valid);
		if(valid){
			progress.setProgress(100.0);
		}
	}
}