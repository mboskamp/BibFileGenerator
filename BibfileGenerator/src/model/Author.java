package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

	private String name;
	private String firstName;
	
	public Author(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
	}
	
	public String getNameFirstName(){
		return name + ", " + firstName;
	}
}
