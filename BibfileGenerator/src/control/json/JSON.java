package control.json;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Author;
import model.Book;

public class JSON {
	
	private ArrayList<Book> books = new ArrayList<>();
	private JSONParser parser = new JSONParser();

	public JSON() {
		String sample = "{\"index_searched\" : \"isbn\",\"data\" : [{\"publisher_name\" : \"G. Olms\",\"publisher_id\" : \"g_olms\",\"language\" : \"\",\"title_latin\" : \"Studien zum Fruhwerk von Max Liebermann: Mit einem Katalog der Gemalde und Olstudien von 1866-1889\",\"author_data\" : [{\"name\" : \"Katrin Boskamp\",\"id\" : \"katrin_boskamp\"}],\"physical_description_text\" : \"471 pages\",\"summary\" : \"\",\"title_long\" : \"Studien zum Fruhwerk von Max Liebermann: Mit einem Katalog der Gemalde und Olstudien von 1866-1889 (Studien zur Kunstgeschichte)\",\"urls_text\" : \"\",\"publisher_text\" : \"G. Olms\",\"book_id\" : \"studien_zum_fruhwerk_von_max_liebermann_mit_einem_katalog_de\",\"awards_text\" : \"\",\"subject_ids\" : [\"arts_photography_painting_general\",\"nonfiction_foreign_language_nonfiction_german\"],\"isbn13\" : \"9783487098975\",\"dewey_decimal\" : \"\",\"title\" : \"Studien zum Fruhwerk von Max Liebermann: Mit einem Katalog der Gemalde und Olstudien von 1866-1889\",\"lcc_number\" : \"\",\"isbn10\" : \"3487098970\",\"edition_info\" : \"Turtleback; 1994\",\"marc_enc_level\" : \"~\",\"dewey_normal\" : \"0\",\"notes\" : \"\"}]}";
		try {

			Object obj = parser.parse(sample);
			
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray data = (JSONArray) jsonObject.get("data");
			Iterator<JSONObject> iterator = data.iterator();
			while (iterator.hasNext()) {
				JSONObject book = iterator.next();
				books.add(parseBook(book));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private Book parseBook(JSONObject book){
		String publisher = (String) book.get("publisher_name");
		
		String title = (String) book.get("title_long");
		
		String isbn10 = (String) book.get("isbn10");
		String isbn13 = (String) book.get("isbn13");
		
		JSONArray author_data = (JSONArray) book.get("author_data");
		String fullName = (String) author_data.get(0);
		String[] names = fullName.split(", ");
		String name = names[0];
		String firstName = names[1];
		Author author = new Author(name, firstName);
		
		Book bookObj = new Book();
		
		
		return bookObj;
	}
}
