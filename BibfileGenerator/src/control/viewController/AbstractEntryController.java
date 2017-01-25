package control.viewController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.StringValue;
import org.jbibtex.Value;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Abstract superclass for any view controller that is used to gather
 * information about a new entry in the Bibfile.<br>
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractEntryController extends AbstractController {
	
	protected static Map<String, Key> fields;
	static {
        Map<String, Key> staticFieldsMap = new HashMap<>();
        staticFieldsMap.put("address", BibTeXEntry.KEY_ADDRESS);
    	staticFieldsMap.put("annote", BibTeXEntry.KEY_ANNOTE);
    	staticFieldsMap.put("author", BibTeXEntry.KEY_AUTHOR);
    	staticFieldsMap.put("booktitle", BibTeXEntry.KEY_BOOKTITLE);
    	staticFieldsMap.put("chapter", BibTeXEntry.KEY_CHAPTER);
    	staticFieldsMap.put("crossref", BibTeXEntry.KEY_CROSSREF);
    	staticFieldsMap.put("doi", BibTeXEntry.KEY_DOI);
    	staticFieldsMap.put("edition", BibTeXEntry.KEY_EDITION);
    	staticFieldsMap.put("editor", BibTeXEntry.KEY_EDITOR);
    	staticFieldsMap.put("eprint", BibTeXEntry.KEY_EPRINT);
    	staticFieldsMap.put("howpublished", BibTeXEntry.KEY_HOWPUBLISHED);
    	staticFieldsMap.put("institution", BibTeXEntry.KEY_INSTITUTION);
    	staticFieldsMap.put("journal", BibTeXEntry.KEY_JOURNAL);
    	staticFieldsMap.put("key", BibTeXEntry.KEY_KEY);
    	staticFieldsMap.put("month", BibTeXEntry.KEY_MONTH);
    	staticFieldsMap.put("note", BibTeXEntry.KEY_NOTE);
    	staticFieldsMap.put("number", BibTeXEntry.KEY_NUMBER);
    	staticFieldsMap.put("organization", BibTeXEntry.KEY_ORGANIZATION);
    	staticFieldsMap.put("pages", BibTeXEntry.KEY_PAGES);
    	staticFieldsMap.put("publisher", BibTeXEntry.KEY_PUBLISHER);
    	staticFieldsMap.put("school", BibTeXEntry.KEY_SCHOOL);
    	staticFieldsMap.put("series", BibTeXEntry.KEY_SERIES);
    	staticFieldsMap.put("title", BibTeXEntry.KEY_TITLE);
    	staticFieldsMap.put("type", BibTeXEntry.KEY_TYPE);
    	staticFieldsMap.put("url", BibTeXEntry.KEY_URL);
    	staticFieldsMap.put("volume", BibTeXEntry.KEY_VOLUME);
    	staticFieldsMap.put("year", BibTeXEntry.KEY_YEAR);
        
    	fields = Collections.unmodifiableMap(staticFieldsMap);
    }

	@FXML
	public TextField title;

	@FXML
	public TextField year;

	@FXML
	public TextField month;

	@FXML
	public TextField note;

	@FXML
	public TextField key;

	@FXML
	public TextField referenceKey;

	@FXML
	public abstract void initialize();

	// @FXML
	// public abstract void finalize();
	
	public abstract Key getEntryType();

	/**
	 * Called when the user hits the 'add' button in the NewEntryView. Adds all TextField values to the {@link BibTeXEntry}.
	 * @return The reference key to retrieve the saved entry from {@link GlobalStorage}.
	 */
	public BibTeXEntry saveData(){
		Map<Key, Value> values = new HashMap<Key, Value>();
		for (Field field : getFields()) {
			if(field.getType() == TextField.class){
				Key key = AbstractEntryController.fields.get(field.getName());
				if(key != null){
					try {
						values.put(key, new StringValue(((TextField) field.get(this)).getText()));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						//TODO Show error dialog
						e.printStackTrace();
					} catch (Exception e) {
						//TODO Remove after all fields are implemented
						System.out.println("Field: " + field.getName() + " not in form");
					}
				}
			}
		}
		BibTeXEntry entry = new BibTeXEntry(getEntryType(), new Key(referenceKey.getText()));
		entry.addAllFields(values);
		return entry;
	}
	
	private List<Field> getFields(){
		Class<? extends Object> clazz = this.getClass();
		ArrayList<Field> fields = new ArrayList<>();
		do{
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = (Class<? extends Object>) clazz.getSuperclass();
		}while((clazz != AbstractController.class));
		return fields;
	}
}