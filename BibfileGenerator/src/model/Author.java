package model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an author of a book that was fetched from an ISBN request or was
 * typed in manually via GUI.
 * 
 * @author Miklas Boskamp
 */
@Getter
@Setter
public class Author {

	private String name;
	private String firstName;

	public Author(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
	}

	/**
	 * Returns the name of the author in this format: "Name, Firstname".<br/>
	 * <b>Example:</b> "Doe, John"
	 * @return
	 */
	public String getNameFirstName() {
		return name + ", " + firstName;
	}
}
