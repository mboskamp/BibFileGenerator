package control.viewController;

import java.util.HashMap;
import java.util.Map;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.StringValue;
import org.jbibtex.Value;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_BOOK book}.
 * 
 * @author Miklas Boskamp
 */
public class BookController extends AbstractISBNController {

	private final static Key authorKey = BibTeXEntry.KEY_AUTHOR;
	private final static Key editorKey = BibTeXEntry.KEY_EDITOR;
	private final static Key titleKey = BibTeXEntry.KEY_TITLE;
	private final static Key publisherKey = BibTeXEntry.KEY_PUBLISHER;
	private final static Key yearKey = BibTeXEntry.KEY_YEAR;
	private final static Key volumeKey = BibTeXEntry.KEY_VOLUME;
	private final static Key seriesKey = BibTeXEntry.KEY_SERIES;
	private final static Key addressKey = BibTeXEntry.KEY_ADDRESS;
	private final static Key editionKey = BibTeXEntry.KEY_EDITION;
	private final static Key monthKey = BibTeXEntry.KEY_MONTH;
	private final static Key noteKey = BibTeXEntry.KEY_NOTE;
	private final static Key keyKey = BibTeXEntry.KEY_KEY;
	
	
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

	@Override
	public BibTeXEntry saveData() {
		Map<Key, Value> fields = new HashMap<Key, Value>();
		fields.put(authorKey, new StringValue(author.getText()));
		fields.put(editorKey, new StringValue(editor.getText()));
		fields.put(titleKey, new StringValue(title.getText()));
		fields.put(publisherKey, new StringValue(publisher.getText()));
		fields.put(yearKey, new StringValue(year.getText()));
		fields.put(volumeKey, new StringValue(volume.getText()));
		fields.put(seriesKey, new StringValue(series.getText()));
		fields.put(addressKey, new StringValue(address.getText()));
		fields.put(editionKey, new StringValue(edition.getText()));
		fields.put(monthKey, new StringValue(month.getText()));
		fields.put(noteKey, new StringValue(note.getText()));
		fields.put(keyKey, new StringValue(key.getText()));
		
		BibTeXEntry book = new BibTeXEntry(BibTeXEntry.TYPE_BOOK, new Key(referenceKey.getText()));
		book.addAllFields(fields);
		return book;
	}
}