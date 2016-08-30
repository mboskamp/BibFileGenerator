package model.isbn;

import control.isbn.ISBNFactory;
import control.isbn.ISBNUtils;
import lombok.Getter;
import lombok.Setter;
import model.isbn.ISBNType;

/**
 * Represents an ISBN object. The constructor should not be used directly. You
 * should use {@link ISBNUtils#validateAndReturn(String)} to get your ISNN
 * object because a validation will be performed.
 * 
 * @author Miklas Boskamp
 */
@Getter
@Setter
public class ISBN {

	private String isbn;
	private ISBNType type;

	/**
	 * Should not be called directly. Use {@link ISBNFactory} to create ISBN
	 * objects.
	 * 
	 * @param isbn
	 *            The ISBN string
	 */
	public ISBN(String isbn) {
		this.isbn = ISBNUtils.cleanISBNString(isbn);
		if (this.isbn.length() == 10) {
			this.type = ISBNType.ISBN10;
		} else if (this.isbn.length() == 13) {
			this.type = ISBNType.ISBN13;
		}
	}

	/**
	 * Returns the ISBN as a formated string.<br/>
	 * Examples:
	 * <ul>
	 * <li>ISBN13: 978-3-16-148410-0</li>
	 * <li>ISBN10: 3-86680-192-0 or 3-690-08783-X</li>
	 * </ul>
	 * 
	 * @return the formated string
	 */
	public String toFormattedString() {
		if (type == ISBNType.ISBN10) {
			return isbn.substring(0, 1) + "-" + isbn.substring(1, 6) + "-" + isbn.substring(6, 9) + "-"
					+ isbn.charAt(9);
		} else {
			return isbn.substring(0, 3) + "-" + isbn.charAt(3) + "-" + isbn.substring(4, 9) + "-"
					+ isbn.substring(9, 12) + "-" + isbn.charAt(12);
		}
	}

	@Override
	/**
	 * Returns the unformatted string. (Without hyphens)
	 */
	public String toString() {
		return isbn;
	}
}