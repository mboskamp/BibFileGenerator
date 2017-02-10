package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private Author[] author;
	private String title;
	private String journal;
	private String year;
	
	private String volume;
	private String number;
	private String pages;
	private String month;
	private String note;
	private String key;
	
	public String getAuthors(){
		String re = "";
		for (int i = 0; i < author.length; i++) {
			re += author[i].getNameFirstName();
			if(i != author.length-1){
				re += " and ";
			}
		}
		return re;
	}
}
