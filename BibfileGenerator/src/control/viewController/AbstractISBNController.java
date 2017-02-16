package control.viewController;

import java.util.ArrayList;

import control.error.Error;
import control.error.ExceptionDialog;
import control.isbn.ISBNUtils;
import control.json.JSONUtils;
import control.net.NetUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Book;
import model.isbn.ISBN;
import view.bibComponent.EntryTextField;

/**
 * Abstract superclass for any publication that has a ISBN number. <br>
 * The following controllers inherit the AbstractConferenceController:<br>
 * <ul>
 * <li>{@link BookController}</li>
 * <li>{@link InbookController}</li>
 * <li>{@link IncollectionController}</li>
 * </ul>
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractISBNController extends AbstractPrintEntryController {

	@FXML
	public Button isbnSearchButton;

	@FXML
	public EntryTextField isbnSearch;

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
	public EntryTextField publisher;

	@FXML
	public EntryTextField edition;

	@FXML
	public EntryTextField address;

	@FXML
	public EntryTextField series;

	/**
	 * Volume/Number
	 */
	@FXML
	public EntryTextField volume;

	/**
	 * Volume/Number
	 */
	@FXML
	public EntryTextField number;

	@FXML
	public EntryTextField editor;

	@FXML
	private EntryTextField isbn;

	private boolean validISBN = false;

	private static final String ISBN_ERROR = "ISBN ungültig";

	@Override
	public void initialize() {
		isbn10Label.setVisible(false);
		isbn13Label.setVisible(false);

		isbnSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("textfield changed from " + oldValue + " to " + newValue);
			validateISBN();
			isbnError.setText("");
		});
	}

	@FXML
	public void searchISBN() {

		validateISBN(); // FIXME Remove after default ISBN is removed

		if (validISBN) {
			ISBN i = ISBNUtils.validateAndReturn(isbnSearch.getText());
			String json = NetUtils.fireISBNRequest(i);
			ArrayList<Book> books = JSONUtils.parseJSONBookResponse(json);
			if (books != null && books.size() > 0) {
				Book b = books.get(0);
				author.setText(b.getAuthors());
				title.setText(b.getTitle());
				publisher.setText(b.getPublisher());
				year.setText(b.getYear());
				// TODO volume
				// TODO number
				// TODO series
				// TODO address

				try {
					isbn.setText(i.toString());
				} catch (NullPointerException e) {
					//Do nothing. -> Inbook
				}
				if (b.getImage() != null) {
					image.setImage(SwingFXUtils.toFXImage(b.getImage(), null));
				}

				isbn10Label.setVisible(true);
				isbn13Label.setVisible(true);
				isbn10.setText(ISBNUtils.formatISBN(i.getIsbn10()));
				isbn13.setText(ISBNUtils.formatISBN(i.getIsbn13()));
			} else {
				new ExceptionDialog(Error.ISBN_NOT_FOUND, (Exception) null);
			}
		} else {
			isbnError.setText(ISBN_ERROR);
		}
	}

	@FXML
	public void validateISBN() {
		validISBN = ISBNUtils.validateISBN(isbnSearch.getText());
		System.out.println(validISBN ? "ISBN valid" : "ISBN invalid");
	}
}