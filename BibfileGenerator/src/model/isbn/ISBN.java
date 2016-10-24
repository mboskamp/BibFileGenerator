package model.isbn;

import control.isbn.ISBNFactory;
import control.isbn.ISBNUtils;
import lombok.Getter;
import lombok.Setter;
import model.isbn.ISBNType;

/**
 * Represents an ISBN object. The constructor should not be used directly. You
 * should use {@link ISBNUtils#validateAndReturn(String)} to get your ISBN
 * object because a validation will be performed.
 * 
 * @author Miklas Boskamp
 */
@Getter
@Setter
public class ISBN {

	private String isbn10;
	private String isbn13;

	/**
	 * Should not be called directly. Use {@link ISBNFactory} to create ISBN
	 * objects.
	 * 
	 * @param isbn
	 *            The ISBN string
	 */
	public ISBN(String isbn) {
		if (isbn.length() != 10 || isbn.length() != 13) {
			isbn = ISBNUtils.cleanISBNString(isbn);
		}
		if (isbn.length() == 10) {
			isbn10 = isbn;
			isbn13 = ISBNUtils.convertISBNType(isbn);
		} else if (isbn.length() == 13) {
			isbn13 = isbn;
			isbn10 = ISBNUtils.convertISBNType(isbn);
		} else {
			throw new RuntimeException("Invalid ISBN length");
		}
	}

	/**
	 * Returns the ISBN10 as a formated string.<br/>
	 * Examples:
	 * <ul>
	 * <li>3-86680-192-0</li>
	 * <li>3-69008-783-X</li>
	 * </ul>
	 * 
	 * @return the formated string
	 */
	private String formatISBN10() {
		return isbn10.substring(0, 1) + "-" + isbn10.substring(1, 6) + "-" + isbn10.substring(6, 9) + "-"
				+ isbn10.charAt(9);
	}

	/**
	 * Returns the ISBN13 as a formated string.<br/>
	 * Example: <code>ISBN13: 978-3-16-148410-0</code>
	 * 
	 * @return the formated string
	 */
	private String formatISBN13() {
		return isbn13.substring(0, 3) + "-" + isbn13.charAt(3) + "-" + isbn13.substring(4, 9) + "-"
				+ isbn13.substring(9, 12) + "-" + isbn13.charAt(12);
	}

	@Override
	/**
	 * Returns the unformatted ISBN10 string. (Without hyphens)
	 */
	public String toString() {
		return isbn10;
	}

	/**
	 * Returns the ISBN String matching the given {@link ISBNType}.
	 * 
	 * @param type
	 *            The {@link ISBNType} to return
	 * @param formatted
	 *            <code>true</code> for formatted Strings and <code>false</code>
	 *            for unformatted Strings
	 * @return The either formatted or unformatted ISBN String matching the
	 *         given {@link ISBNType}
	 */
	public String toString(ISBNType type, boolean formatted) {
		if (formatted) {
			if (type == ISBNType.ISBN10) {
				return formatISBN10();
			} else if (type == ISBNType.ISBN13) {
				return formatISBN13();
			}
		} else {
			if (type == ISBNType.ISBN10) {
				return isbn10;
			} else if (type == ISBNType.ISBN13) {
				return isbn13;
			}
		}
		return null;
	}
}