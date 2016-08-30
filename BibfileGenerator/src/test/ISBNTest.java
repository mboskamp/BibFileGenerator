package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import control.isbn.ISBNUtils;
import model.isbn.ISBN;

public class ISBNTest {

	/**
	 * Runs this ISBN test. The test reads a test case file from
	 * <code>/src</code> and validates accordingly.
	 */
	public void run() {
		InputStream in = getClass().getClassLoader().getResourceAsStream("isbn_test_cases");
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
						try {
							ISBNUtils.validateAndReturn(line);
						} catch (RuntimeException e) {
							System.out.println("read invalid isbn: " + line);
						}
					} else {
						try {
							ISBN isbn = ISBNUtils.validateAndReturn(line);
							System.out.println("read valid isbn: " + isbn.toFormattedString());
						} catch (RuntimeException e) {
							e.printStackTrace();
						}
					}
				} else {
					modecount++;
					System.out.println("mode " + modecount + " " + modes[modecount]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes a line from the test case file with two space-separated ISBN number
	 * of different type. Calls {@link ISBNUtils#convertISBNType(String)}.
	 * @param line the line from the test case file containing two space-separated ISBN numbers.
	 */
	private void convert(String line) {
		String[] isbns = line.split(" ");
		String converted = ISBNUtils.convertISBNType(isbns[0]);
		isbns[1] = ISBNUtils.cleanISBNString(isbns[1]);
		if (!converted.equals(isbns[1])) {
			throw new RuntimeException("Error converting " + isbns[0] + " to " + isbns[1]);
		} else {
			System.out.println("converted " + isbns[0] + " to " + isbns[1]);
		}
	}
}