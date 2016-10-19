package control.viewController;

import java.util.ArrayList;

import control.isbn.ISBNUtils;
import control.json.JSONUtils;
import control.net.NetUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Book;
import model.isbn.ISBN;

public abstract class AbstractISBNController extends AbstractPrintEntryController {
	
	@FXML
	public Button isbnSearchButton;
	
	@FXML
	public TextField isbn;
	
	@FXML
	public Label isbnError;
	
	@FXML
	public Label isbn10;
	
	@FXML
	public Label isbn13;
	
	@FXML
	public Label isbn10Label;
	
	@FXML
	public Label isbn13Label;
	
	@FXML
	public ImageView image;
	
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
	
	private static final String ISBN_ERROR = "ISBN ungültig";
	
	@Override
	public void initialize(){
		isbn10Label.setVisible(false);
		isbn13Label.setVisible(false);
		
		isbn.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("textfield changed from " + oldValue + " to " + newValue);
		    validateISBN();
		});
	}

	@FXML
	public void searchISBN(){
		validateISBN(); //TODO remove after testing
		
		if(validISBN){
			ISBN i = ISBNUtils.validateAndReturn(isbn.getText());
			String json = NetUtils.fireISBNRequest(i);
			ArrayList<Book> books = JSONUtils.parseJSONBookResponse(json);
			if(books != null && books.size() > 0){
				Book b = books.get(0); //TODO selection
				author.setText(b.getAuthors());
				title.setText(b.getTitle());
				publisher.setText(b.getPublisher());
				year.setText(b.getYear());
				//TODO volume
				//TODO number
				//TODO series
				//TODO address
				
				if(b.getImage() != null){
					image.setImage(SwingFXUtils.toFXImage(b.getImage(), null));
				}
				
				isbn10Label.setVisible(true);
				isbn13Label.setVisible(true);
				isbn10.setText(ISBNUtils.formatISBN(i.getIsbn10()));
				isbn13.setText(ISBNUtils.formatISBN(i.getIsbn13()));				
			}
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