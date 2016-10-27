package model.bib;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXFormatter;

import lombok.Getter;
import lombok.Setter;

/**
 * A Bibfile holds a {@link BibTeXDatabase} and is able to export its content.
 * 
 * @author Miklas Boskamp
 */
public class BibFile {
	@Getter
	@Setter
	private BibTeXDatabase database;
	private BibTeXFormatter formatter = new BibTeXFormatter();

	public BibFile(BibTeXDatabase database) {
		this.database = database;
	}

	/**
	 * Shortcut for
	 * <code>{@link #getDatabase()}.{@link BibTeXDatabase#addObject(BibTeXObject)}</code>
	 * Adds the given {@link BibTeXEntry} to the {@link BibTeXDatabase} if it is
	 * not already in there.
	 * 
	 * @see BibTeXDatabase#addObject(BibTeXObject)
	 * @param entry
	 *            The {@link BibTeXEntry} that will be added to the
	 *            {@link BibTeXDatabase}
	 */
	public void add(BibTeXEntry entry) {
		database.addObject(entry);
	}

	/**
	 * Shortcut for
	 * <code>{@link #getDatabase()}.{@link BibTeXDatabase#removeObject(BibTeXObject)}</code>
	 * Removes the given {@link BibTeXEntry} from the {@link BibTeXDatabase} if
	 * it exists.
	 * 
	 * @see BibTeXDatabase#addObject(BibTeXObject)
	 * @param entry
	 *            The {@link BibTeXEntry} that will be removed from the
	 *            {@link BibTeXDatabase}
	 */
	public void remove(BibTeXEntry entry) {
		database.removeObject(entry);
	}

	// TODO Testing
	/**
	 * EXPERIMENTAL<br>
	 * Exports the BibFile as a file that will be stored under the given path.
	 * 
	 * @param path
	 *            The path where the file will be stored.
	 */
	public void exportFile(String path) {

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			formatter.format(database, writer);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Error while exporting
			e.printStackTrace();
		}
	}

	// TODO Testing
	/**
	 * EXPERIMENTAL
	 * Exports the BibFile as String.
	 * 
	 * @return The string representation of this BibFile.
	 */
	public String exportString() {
		StringWriter writer = new StringWriter();
		try {
			formatter.format(database, writer);
		} catch (IOException e) {
			// TODO Error while exporting
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * A non-empty BibFile must have at least one {@link BibTeXEntry} in its
	 * database. If the database has no elements, the {@link BibFile} is
	 * considered empty.
	 * 
	 * @return <code>false</code> if the Bibfile's database contains no element,
	 *         otherwise <code>true</code>
	 */
	public boolean isEmpty() {
		return database.getObjects().size() == 0 ? false : true;
	}

	@Override
	public String toString() {
		return exportString();
	}
}