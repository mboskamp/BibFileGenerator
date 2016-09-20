package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import control.isbn.ISBNUtils;
import junit.framework.Assert;
import model.isbn.ISBN;

public class ISBNTest {

	/**
	 * Runs this ISBN test. The test reads a test case file from
	 * <code>/src</code> and validates accordingly.
	 */
	@Test
	public void run() {
		InputStream in = getClass().getClassLoader().getResourceAsStream("test/isbn_test_cases");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		String[] modes = { "10_valid_clean", "10_valid_dirty", "10_invalid_clean", "13_valid_clean", "13_invalid_clean",
				"13_valid_dirty", "convert10to13", "convert13to10" };
		int modecount = -1;
		try {
			while ((line = reader.readLine()) != null) {
				if (!(line.equals("") || line.contains("mode"))) {
					if (modes[modecount].contains("convert")) {
						convert(line);
					} else if (modes[modecount].contains("invalid")) {
						ISBN isbn = ISBNUtils.validateAndReturn(line);
						Assert.assertNull(isbn);
					} else {
						ISBN isbn = ISBNUtils.validateAndReturn(line);
						Assert.assertNotNull(isbn);
					}
				} else {
					modecount++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes a line from the test case file with two space-separated ISBN number
	 * of different type. Calls {@link ISBNUtils#convertISBNType(String)}.
	 * 
	 * @param line
	 *            the line from the test case file containing two
	 *            space-separated ISBN numbers.
	 */
	private void convert(String line) {
		String[] isbns = line.split(" ");
		String converted = ISBNUtils.convertISBNType(isbns[0]);
		isbns[1] = ISBNUtils.cleanISBNString(isbns[1]);
		Assert.assertEquals(isbns[1], converted);
	}
}