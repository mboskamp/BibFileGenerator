package control.viewController;

import java.util.HashMap;
import java.util.Map;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.StringValue;
import org.jbibtex.Value;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.GlobalStorage;

/**
 * Controller that handles the input of user data about an
 * {@link BibTeXEntry#TYPE_ARTICLE article}.
 * 
 * @author Miklas Boskamp
 */
public class ArticleController extends AbstractPrintEntryController {

	private final static Key authorKey = BibTeXEntry.KEY_AUTHOR;
	private final static Key titleKey = BibTeXEntry.KEY_TITLE;
	private final static Key journalKey = BibTeXEntry.KEY_JOURNAL;
	private final static Key yearKey = BibTeXEntry.KEY_YEAR;
	private final static Key volumeKey = BibTeXEntry.KEY_VOLUME;

	private final static Key numberKey = BibTeXEntry.KEY_NUMBER;
	private final static Key pagesKey = BibTeXEntry.KEY_PAGES;
	private final static Key monthKey = BibTeXEntry.KEY_MONTH;
	private final static Key noteKey = BibTeXEntry.KEY_NOTE;
	private final static Key keyKey = BibTeXEntry.KEY_KEY;

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
		//Do nothing
	}

	@Override
	public String saveData() {
		Map<Key, Value> fields = new HashMap<Key, Value>();
		fields.put(authorKey, new StringValue(author.getText()));
		fields.put(journalKey, new StringValue(journal.getText()));
		fields.put(titleKey, new StringValue(title.getText()));
		fields.put(yearKey, new StringValue(year.getText()));
		fields.put(volumeKey, new StringValue(volume.getText()));
		fields.put(numberKey, new StringValue(number.getText()));
		fields.put(pagesKey, new StringValue(pages.getText()));
		fields.put(monthKey, new StringValue(month.getText()));
		fields.put(noteKey, new StringValue(note.getText()));
		fields.put(keyKey, new StringValue(key.getText()));

		BibTeXEntry book = new BibTeXEntry(BibTeXEntry.TYPE_BOOK, new Key(referenceKey.getText()));
		book.addAllFields(fields);

		String key = author.getText() + title.getText() + year.getText();
		GlobalStorage.getInstance().persist(key, book);
		return key;
	}
}