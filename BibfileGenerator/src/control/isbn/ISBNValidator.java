package control.isbn;

/**
 * Provides static access to ISBN validation functionality.
 * <code>ISBNValidator</code> can validate ISBN-10 and ISBN-13. Please refer to
 * {@link #validate10(String)} and {@link ISBNValidator#validate13(String)}. If
 * you are not sure which type of ISBN number you have, call
 * {@link #validateISBN(String)}.
 * 
 * @author Miklas Boskamp
 */
public class ISBNValidator {

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
	 *         {@link #validate13(String)}.
	 */
	public static boolean validateISBN(String input) {		
		String isbn = "";
		for (int i = 0; i < input.toCharArray().length; i++) {
			char c = input.toCharArray()[i];
			if (Character.isDigit(c) || (c == 'X' && isbn.length() == 9)) {
				isbn += c;
			}
		}
		if (isbn.length() == 10) {
			return validate10(isbn);
		} else if (isbn.length() == 13) {
			return validate13(isbn);
		}
		throw new RuntimeException("ISBN is malformed or has an invalid length");
	}

	/**
	 * Takes a string containing a ISBN-13 without any characters other than
	 * digits. Call {@link #validateISBN(String)} to strip off those characters.
	 * This method validates the given string. Validations works as followed:
	 *
	 * <h4>Example:</h4> input: 9783765727818 <br/>
	 * All digits except the last one will be added together after being
	 * multiplied by either <code>1</code> or <code>3</code>. The first digit is
	 * multiplied by <code>1</code> the next by <code>3</code>, next
	 * <code>1</code> and so on.<br/>
	 * In this example:
	 * <code>9x1 + 7x3 + 8x1 + 3x3 + 1x1 + 2x3 + 7x1 + 3x3 + 2x1 + 3x3 + 2x1 + 0x3 = 83</code><br/>
	 * The difference to the next multiple of 10 is the last digit. (checksum
	 * digit)<br/>
	 * <br/>
	 * If that digit equals the last digit in the string the ISBN is valid.
	 * 
	 * @param isbn
	 *            The ISBN number as a String. Can <u>not</u> contain hyphens
	 *            and/or whitespace.
	 * @return <code>true</code> if the ISBN is valid and <code>false</code> if
	 *         it is invalid.
	 */
	public static boolean validate13(String isbn) {
		if(isbn.length() != 13){
			throw new RuntimeException("ISBN has Invalid length");
		}
		int sum = 0;
		for (int i = 1; i < 13; i++) {
			int n = Character.getNumericValue(isbn.charAt(i - 1));
			int multiplicator = i % 2 == 0 ? 3 : 1;
			sum += multiplicator * n;
		}
		int check = 10 - (sum % 10);
		return Character.getNumericValue(isbn.charAt(12)) == check;
	}

	/**
	 * Takes a string containing a ISBN-10 without any characters other than
	 * digits. The last character can be <code>'X'</code>. Call
	 * {@link #validateISBN(String)} to strip off those characters. This method
	 * validates the given string. Validations works as followed:
	 *
	 * <h4>Example:</h4> input: 3866801920 <br/>
	 * All digits except the last one will be added together after being
	 * multiplied by their position index (first digit => index = 1). In this
	 * example:
	 * <code>3x1 + 8x2 + 6x3 + 6x4 + 8x5 + 0x6 + 1x7 + 9x8 + 2x9 = 198</code><br/>
	 * The last digit must equal the the sum modulo 11. (198 mod 11 = 0, 11 x 18
	 * = 198)<br/>
	 * If that digit equals the last digit in the string the ISBN is valid.
	 * 
	 * @param isbn
	 *            The ISBN number as a String. Can <u>not</u> contain hyphens
	 *            and/or whitespace.
	 * @return <code>true</code> if the ISBN is valid and <code>false</code> if
	 *         it is invalid.
	 */
	public static boolean validate10(String isbn) {
		if(isbn.length() != 10){
			throw new RuntimeException("ISBN has Invalid length");
		}
		int sum = 0;
		for (int i = 1; i < 10; i++) {
			int n = Character.getNumericValue(isbn.charAt(i - 1));
			sum += i * n;
		}
		int check = sum % 11;
		if (check == 10) {
			return isbn.charAt(9) == 'X';
		}
		return Character.getNumericValue(isbn.charAt(9)) == check;
	}
}
