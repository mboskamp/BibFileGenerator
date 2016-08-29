package model;

import control.isbn.ISBNFactory;
import control.isbn.ISBNUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ISBN {

	private String isbn;
	private ISBNType type;

	/**
	 * Should not be called directly. Use {@link ISBNFactory} to create ISBN objects. 
	 * @param isbn The ISBN string
	 */
	public ISBN(String isbn) {
		this.isbn = ISBNUtils.cleanISBNString(isbn);
		if(this.isbn.length() == 10){
			this.type = ISBNType.ISBN10;
		}else if(this.isbn.length() == 13){
			this.type = ISBNType.ISBN13;
		}
	}

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
	public String toString() {
		return isbn;
	}
}