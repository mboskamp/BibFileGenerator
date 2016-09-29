package control.bib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXFormatter;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import model.bib.BibFile;

/**
 * Provides static access to {@link BibFile} operations like exporting and importing files.
 * 
 * @author Miklas Boskamp
 */
public class BibUtils {
	private BibUtils() {
	}

	/**
	 * Exports the {@link BibTeXDatabase} to File that contains the formatted BibTeX code.
	 * 
	 * @param database The database that contains the {@link BibTeXEntry BibTeXEntries}.
	 * @param path The path to the export file
	 * @return the formatted file
	 */
	public static File exportToFile(BibTeXDatabase database, String path) {
		if (database.getObjects().size() != 0) {
			BibTeXFormatter bibtexFormatter = new BibTeXFormatter();
			File file = new File(path);
			try {
				FileWriter fw = new FileWriter(file);
				bibtexFormatter.format(database, fw);
			} catch (IOException e) {
				// File inaccessible -> return null.
			}
			return file;
		}
		return null;
	}

	/**
	 * Takes the path to a String and calls {@link #readFile(File)}.
	 * 
	 * @param path The path to the File that should be read.
	 * @return The produced {@link BibFile} or <code>Null</code> if the BibFile could not be produced.
	 */
	public static BibFile readFile(String path) {
		return readFile(new File(path));
	}

	/**
	 * Takes an reads a File containing valid BibTex data. The data is parsed by
	 * a {@link BibTeXParser}. If no errors occurred a {@link BibFile} is
	 * returned. The return value is <code>null</code> if for any reason the
	 * file could not be converted to a {@link BibFile} object.
	 * 
	 * @param file The File object that contains valid BibTex data
	 * @return The produced {@link BibFile} or <code>Null</code> if the BibFile could not be produced.
	 */
	public static BibFile readFile(File file) {
		FileReader reader;
		try {
			reader = new FileReader(file);
			BibTeXParser parser = new BibTeXParser();
			return new BibFile(parser.parse(reader));
		} catch (FileNotFoundException e) {
			// File not Found. Return null.
		} catch (TokenMgrException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO: Exception while parsing.
		}
		return null;
	}
}
