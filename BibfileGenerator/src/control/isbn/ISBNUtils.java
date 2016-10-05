package control.isbn;

import model.isbn.ISBN;

/**
 * Provides static access to ISBN validation functionality.
 * <code>ISBNUtils</code> can validate ISBN-10 and ISBN-13. Please refer to
 * {@link #validate10(String)} and {@link ISBNUtils#validate13(String)}. If you
 * are not sure which type of ISBN number you have, call
 * {@link #validateISBN(String)}.
 * 
 * @author Miklas Boskamp
 */
public class ISBNUtils {

	private static final String LENGTH_ERROR_MESSAGE = "ISBN has Invalid length";

	/**
	 * If {@link #validateISBN(String)} gives <code>true</code> the valid ISBN
	 * will be used to create an {@link ISBN} Object, which is returned.
	 * 
	 * @param input
	 *            The ISBN number as a String. Can contain hyphens and/or
	 *            whitespace.
	 * @return An {@link ISBN} Object or null if the input String is not a valid ISBN
	 */
	public static ISBN validateAndReturn(String isbn) {
		if (validateISBN(isbn)) {
			return new ISBN(isbn);
		}
		return null;
	}

	/**
	 * Takes an ISBN number as a String value. All characters that are not
	 * digits, such as hyphens and whitespace, will be stripped off. The pure
	 * digit string will be checked for length. Any 13-digit string will be
	 * validated via {@link #validate13(String)} and any 10-digit string will be
	 * validated via {@link #validate10(String)}. You can call each method
	 * directly if you already know the type of your ISBN number.
	 * 
	 * @param input
	 *            The ISBN number as a String. Can contain hyphens and/or
	 *            whitespace.
	 * @return The result of either {@link #validate10(String)} or
	 *         {@link #validate13(String)} or <code>false</code> if the cleaned
	 *         input is neither 10 nor 13 characters long.
	 */
	public static boolean validateISBN(String input) {
		String isbn = cleanISBNString(input);
		if (isbn.length() == 10) {
			return validate10(isbn);
		} else if (isbn.length() == 13) {
			return validate13(isbn);
		}
		return false;
	}

	/**
	 * Takes a string containing a ISBN-13 without any characters other than
	 * digits. Call {@link #validateISBN(String)} to strip off those
	 * characters.<br/>
	 * Validation is done by {@link #calcCheckSum13()}. If the returned digit
	 * equals the last digit in the string the ISBN is valid.
	 * 
	 * @param isbn
	 *            The ISBN number as a String. Can <u>not</u> contain hyphens
	 *            and/or whitespace.
	 * @return <code>true</code> if the ISBN is valid and <code>false</code> if
	 *         it is invalid.
	 */
	public static boolean validate13(String isbn) {
		if (isbn.length() != 13) {
			throw new RuntimeException(LENGTH_ERROR_MESSAGE);
		}
		String sum = calcCheckSum13(isbn);
		return String.valueOf(Character.getNumericValue(isbn.charAt(12))).equals(sum);
	}

	/**
	 * Takes a string containing a ISBN-10 without any characters other than
	 * digits. The last character can be <code>'X'</code>. Call
	 * {@link #validateISBN(String)} to strip off those characters. Validation
	 * is done by {@link #validate10(String)}
	 * 
	 * @param isbn
	 *            The ISBN number as a String. Can <u>not</u> contain hyphens
	 *            and/or whitespace.
	 * @return <code>true</code> if the ISBN is valid and <code>false</code> if
	 *         it is invalid.
	 */
	public static boolean validate10(String isbn) {
		if (isbn.length() != 10) {
			throw new RuntimeException(LENGTH_ERROR_MESSAGE);
		}
		int calculated_sum = calcCheckSum10(isbn);
		int sum;
		if (isbn.charAt(9) == 'X') {
			sum = 10;
		} else {
			sum = Character.getNumericValue(isbn.charAt(9));
		}
		return calculated_sum == sum;
	}

	/**
	 * Converts the given ISBN number to the opposite ISBNType.<br/>
	 * ISBN10 -> ISBN13<br/>
	 * ISBN13 -> ISBN10
	 * 
	 * @param isbn
	 *            The converted ISBN as String without hyphens.
	 * @return
	 */
	public static String convertISBNType(String isbn) {
		isbn = cleanISBNString(isbn);
		if (isbn.length() == 10) {
			isbn = isbn.substring(0, 9);
			isbn = "978" + isbn;
			isbn += calcCheckSum13(isbn);
		} else if (isbn.length() == 13) {
			isbn = isbn.substring(3, 12);
			int check = calcCheckSum10(isbn);
			isbn += check == 10 ? "X" : check;
		}
		return isbn;
	}

	/**
	 * This method strips the input string from any character other than a
	 * digit. ISBN10 allows the character 'X' at the end (index 9), so this is
	 * allowed too.
	 * 
	 * @param input
	 *            THe string to be stripped.
	 * @return The string containing only digits. ('X' at index 9 is allowed)
	 */
	public static String cleanISBNString(String input) {
		String isbn = "";
		for (int i = 0; i < input.toCharArray().length; i++) {
			char c = input.toCharArray()[i];
			if (Character.isDigit(c) || (c == 'X' && isbn.length() == 9)) {
				isbn += c;
			}
		}
		return isbn;
	}

	/**
	 * Returns the formatted ISBN string.<br>
	 * Example:<br>
	 * ISBN10: 3866801920 -> 3-86680-192-0
	 * ISBN13: 9783866801929 -> 978-3-86680-192-9
	 * @param input
	 * @return
	 */
	public static String formatISBN(String input){
		input = cleanISBNString(input);
		if(input.length() == 10){
			return input.substring(0, 1) + "-" + input.substring(1, 6) + "-" + input.substring(6, 9) + "-" + input.substring(9);
		}else if(input.length() == 13){
			return input.substring(0, 3) + "-" + input.substring(3, 4) + "-" + input.substring(4, 9) + "-" + input.substring(9, 12) + "-" + input.substring(12);
		}
		return null;
	}

	/**
	 * This method calculates the checksum for any given 9 digits based on the
	 * rules for ISBN10 checksums.
	 *
	 * <h4>Example:</h4> input: 386680192? <br/>
	 * All digits except the last one will be added together after being
	 * multiplied by their position index (first digit => index = 1). In this
	 * example:
	 * <code>3x1 + 8x2 + 6x3 + 6x4 + 8x5 + 0x6 + 1x7 + 9x8 + 2x9 = 198</code><br/>
	 * The last digit must equal the the sum modulo 11. (198 mod 11 = 0, 11 x 18
	 * = 198)<br/>
	 * If that digit equals the last digit in the string the ISBN is valid.
	 * 
	 * @param isbn
	 *            The ISBN number as string but without the checksum. If more
	 *            than 9 digits are passed, the rest will be ignored.
	 * @return The checksum as string
	 */
	private static int calcCheckSum10(String isbn) {
		int sum = 0;
		for (int i = 1; i < 10; i++) {
			int n = Character.getNumericValue(isbn.charAt(i - 1));
			sum += i * n;
		}
		int check = sum % 11;

		return check;
	}

	/**
	 * This method calculates the checksum for any given 12 digits based on the
	 * rules for ISBN13 checksums.
	 *
	 * <h4>Example:</h4> input: 978376572781? <br/>
	 * All digits except the last one will be added together after being
	 * multiplied by either <code>1</code> or <code>3</code>. The first digit is
	 * multiplied by <code>1</code> the next by <code>3</code>, next
	 * <code>1</code> and so on.<br/>
	 * In this example:
	 * <code>9x1 + 7x3 + 8x1 + 3x3 + 1x1 + 2x3 + 7x1 + 3x3 + 2x1 + 3x3 + 2x1 + 0x3 = 83</code><br/>
	 * The difference to the next multiple of 10 is the last digit. (checksum
	 * digit)<br/>
	 * 
	 * @param isbn
	 *            The ISBN number as string but without the checksum. If more
	 *            than 12 digits are passed, the rest will be ignored.
	 * @return The checksum as string
	 */
	private static String calcCheckSum13(String isbn) {
		int sum = 0;
		for (int i = 1; i < 13; i++) {
			int n = Character.getNumericValue(isbn.charAt(i - 1));
			int multiplicator = i % 2 == 0 ? 3 : 1;
			sum += multiplicator * n;
		}
		int mod = sum % 10;
		int check = 10 - mod;
		return mod == 0 ? String.valueOf(mod) : String.valueOf(check);
	}
}
