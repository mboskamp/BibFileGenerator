package control.viewController;

import control.isbn.ISBNUtils;
import control.net.NetUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML
	private TextField isbn;
	
	@FXML
	private ProgressBar progress;
	
	@FXML
	private Label internet;
	
	//Test
	@FXML
	public void pressButton(){
		System.out.println("pressed");
		progress.setProgress(0.0);
		String input = isbn.getText();
		boolean valid = ISBNUtils.validateISBN(input);
		System.out.println(valid);
		if(valid){
			progress.setProgress(100.0);
		}
	}
	
	//Test
	@FXML
    public void initialize() {
		boolean isInetAvailable = NetUtils.netIsAvailable() ;
		internet.setText(isInetAvailable ? "internet" : "no internet");
		internet.getStyleClass().add(isInetAvailable ? "internet_label_connection" : "internet_label_no_connection");
    }
	
}