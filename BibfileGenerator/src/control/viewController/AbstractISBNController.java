package control.viewController;

import java.util.ArrayList;

import control.isbn.ISBNUtils;
import control.json.JSONUtils;
import control.net.NetUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import model.Book;
import model.isbn.ISBN;

public abstract class AbstractISBNController extends AbstractPrintEntryController {
	
	@FXML
	public Button isbnSearchButton;
	
	@FXML
	public TextField isbn;
	
	@FXML
	public Label isbnError;
	
	/*
	 * FIELDS 
	 */
	@FXML
	public TextField publisher;
	
	@FXML
	public TextField edition;
	
	@FXML
	public TextField address;
	
	@FXML
	public TextField series;
	
	/**
	 * Volume/Number
	 */
	@FXML
	public TextField volume;
	
	/**
	 * Volume/Number
	 */
	@FXML
	public TextField number;
	
	@FXML
	public TextField editor;
	
	private boolean validISBN = false;
	
	private static final String ISBN_ERROR = "Keine gültige ISBN! Überprüfe deine \n Angabe!";
	
	@Override
	public void initialize(){
		super.initialize();
		
		isbn.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("textfield changed from " + oldValue + " to " + newValue);
		    validateISBN();
		});
	}

	@FXML
	public void searchISBN(){
		if(validISBN){
			ISBN i = ISBNUtils.validateAndReturn(isbn.getText());
			String json = NetUtils.fireRequest(i);
			ArrayList<Book> books = JSONUtils.parseJSONResponse(json);
			System.out.println(books);
		}else{
			isbnError.setText(ISBN_ERROR);
		}
	}
	
	@FXML
	public void validateISBN(){
		validISBN = ISBNUtils.validateISBN(isbn.getText());
		System.out.println(validISBN ? "ISBN valid" : "ISBN invalid");
	}
}