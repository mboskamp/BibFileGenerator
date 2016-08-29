package control.isbn;

import model.isbn.ISBN;

/**
 * This Class implements the factory and singleton design pattern. To get the
 * current instance of {@link ISBNFactory}, please call
 * {@link #getInstance()}.<br/>
 * To retrieve an object of {@link ISBN}, you can call {@link #getISBN(String)}.
 * 
 * @author Miklas Boskamp
 */
public class ISBNFactory {

	private static ISBNFactory INSTANCE = null;

	/**
	 * The singleton pattern does not allow a public constructor.
	 */
	private ISBNFactory() {
	}

	/**
	 * Returns the current instance of this class. There can only be one
	 * instance at a time. If there is already one instance of this class, this
	 * instance will be returned.
	 * 
	 * @return The current instance of this class.
	 */
	public static ISBNFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ISBNFactory();
		}
		return INSTANCE;
	}

	/**
	 * Takes a string containing an ISBN10 or ISBN13 number. The ISBN will be
	 * validated and if it is valid a new {@link ISBN} will be returned.
	 * 
	 * @param isbn The string containing the ISBN number.
	 * @return A new {@link ISBN} object containing the given, valid ISBN string.
	 * @throws @{@link RuntimeException} if the given ISBN is not valid.
	 */
	public ISBN getISBN(String isbn) throws RuntimeException{
		return ISBNUtils.validateAndReturn(isbn);
	}
}