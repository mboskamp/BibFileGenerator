package model;

import java.awt.image.RenderedImage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.isbn.ISBN;

/**
 * Represents a book fetched via ISBN request or that was typed in manually via
 * GUI.
 * 
 * @author Miklas Boskamp
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	private Author[] author;
	private String title;
	private String publisher;
	private String year;
	
	private ISBN isbn;
	
	private String number;
	private Long pages;
	private String month;
	private String note;
	private String key;	
	private RenderedImage image;
}