package control.viewController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Entry;
import org.jbibtex.Key;
import org.jbibtex.StringValue;
import org.jbibtex.Value;

import control.error.Error;
import control.error.ExceptionDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import lombok.Getter;
import lombok.Setter;
import view.bibComponent.EntryTextField;

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
		staticFieldsMap.put("crossref", BibTeXEntry.KEY_CROSSREF);

		fields = Collections.unmodifiableMap(staticFieldsMap);
	}

	@Getter
	@Setter
	private Boolean detail;

	@FXML
	public EntryTextField title;

	@FXML
	public EntryTextField year;

	@FXML
	public EntryTextField month;

	@FXML
	public EntryTextField note;

	@FXML
	public EntryTextField crossref;

	@FXML
	public abstract void initialize();

	// @FXML
	// public abstract void finalize();

	public abstract Key getEntryType();

	/**
	 * Called when the user hits the 'add' button in the NewEntryView. Adds all
	 * EntryTextField values to the {@link BibTeXEntry}.
	 * 
	 * @return The reference key to retrieve the saved entry from
	 *         {@link GlobalStorage}.
	 */
	public BibTeXEntry saveData() {
		Map<Key, Value> values = new HashMap<Key, Value>();
		boolean isCorrect = false;
		for (Field field : getFields()) {
			if (field.getType() == EntryTextField.class) {
				Key key = AbstractEntryController.fields.get(field.getName());
				if (key != null) {
					try {
						StringValue value = new StringValue(((EntryTextField) field.get(this)).getText());
						if ((value.getString() != null) && (value.getString().equals("") || value.getString().equals(null)) && ((EntryTextField) field.get(this)).isRequired() && !isCorrect) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Pflichtfeldprüfung");
							alert.setHeaderText("Es wurde nicht alle Pflichtfelder ausgefüllt!");
							alert.setContentText("Es wurden nicht alle Pflichtfelder ausgefüllt. Es kann trotzdem mit der *.bib-Datei gearbeitet werden.");
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK){
								isCorrect = true;
							    continue;
							} else {
							    return null;
							}
						}
						values.put(key, value);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						new ExceptionDialog(Error.ILLEGAL_ACCESS_ERROR, e);
					} catch (NullPointerException npe){
						//Do nothing
					}
				}
			}
		}
		BibTeXEntry entry = new BibTeXEntry(getEntryType(), new Key(crossref.getText()));
		entry.addAllFields(values);
		return entry;
	}

	public BibTeXEntry updateBibTeXEntry(BibTeXEntry bibTeXEntry, int index) {
		boolean save = false;
		Map<String, Value> values = bibTeXEntry.getEntry().getValuesMap();
		for (Field field : getFields()) {
			if (field.getType() == EntryTextField.class) {
				Key key = AbstractEntryController.fields.get(field.getName());
				if (key != null && bibTeXEntry.getEntry().getValuesMap().get(key.toString()) != null) {
					try {
						EntryTextField etf = ((EntryTextField) getField(key.toString()));
						etf.setText(values.get(key.toString()).toUserString());
						if (etf.isChanged()) {
							save = true;
						}

					} catch (NoSuchFieldError nsfe) {
						// Field not found. Do nothing
					} catch (NullPointerException npe) {
						System.out.println("Nullpointer at key: " + key.toString());
					}
				}
			}
		}
		if (save) {
			System.out.println("changed");
			return saveData();
		}
		return null;
	}

	private List<Field> getFields() {
		Class<? extends Object> clazz = this.getClass();
		ArrayList<Field> fields = new ArrayList<>();
		do {
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = (Class<? extends Object>) clazz.getSuperclass();
		} while ((clazz != AbstractController.class));
		return fields;
	}

	private Object getField(String name) {
		Class<?> current = this.getClass();
		do {
			try {
				return current.getDeclaredField(name).get(this);
			} catch (Exception e) {
			}
		} while ((current = current.getSuperclass()) != null);
		return null;
	}
}