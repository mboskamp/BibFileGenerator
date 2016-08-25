package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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