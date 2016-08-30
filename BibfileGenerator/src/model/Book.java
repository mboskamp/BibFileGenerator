package model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a book fetched via ISBN request or that was typed in manually via
 * GUI.
 * 
 * @author Miklas Boskamp
 */
@Getter
@Setter
@SuppressWarnings("unused") //remove when class is implemented
public class Book {

	private Author[] author;
	private String title;
	private String publisher;
	private int year;

	private String isbn10;
	private String isbn13;

	private int number;
	private int pages;
	private String month;
	private String note;
	private String key;

}