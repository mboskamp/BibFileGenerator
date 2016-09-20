package control.json;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.java_cup.internal.runtime.Symbol;

import control.isbn.ISBNUtils;
import model.Author;
import model.Book;
import model.isbn.ISBN;

public class JSONUtils {

	public static void parseJSONResponse(String json) {
		Long numberOfItems = 0L;
		JSONParser parser = new JSONParser();
		ArrayList<Book> books = new ArrayList<>();

		try {
			Object obj = parser.parse(json);

			JSONObject jsonObj = (JSONObject) obj;

			JSONArray items = (JSONArray) jsonObj.get("items");
			numberOfItems = (long) items.size();

			Iterator<JSONObject> iterator = items.iterator();
			while (iterator.hasNext()) {
				books.add(parseItem(iterator.next()));
			}

		} catch (ParseException e) {
			throw new RuntimeException("Error parsing JSON");
		}
	}

	private static Book parseItem(JSONObject json) {
		String title;
		ArrayList<Author> authorsList = new ArrayList<>();
		String publisher = null;
		String year = null;
		String isbn10 = "";
		String isbn13 = "";
		ISBN isbn = null;
		
		String number = null;
		Long pages = null;
		
		JSONObject volumeInfo = (JSONObject) json.get("volumeInfo");
		title = (String) volumeInfo.get("title");
		publisher = (String) volumeInfo.get("publisher");
		year = (String) volumeInfo.get("publishedDate");
		
		JSONArray authorsJSON = (JSONArray) volumeInfo.get("authors");
		Iterator<String> authorIterator = authorsJSON.iterator();
		while (authorIterator.hasNext()) {
			String[] names = authorIterator.next().split(" ");
			String name = names[names.length-1];
			String firstName = "";
			for(int i = 0; i < names.length-1; i++){
				firstName += names[i];
			}
			authorsList.add(new Author(name, firstName));
		}
		Author[] authorsArray = new Author[authorsList.size()];
		authorsArray = authorsList.toArray(authorsArray);
		
		JSONArray identifiers = (JSONArray) volumeInfo.get("industryIdentifiers");
		Iterator<JSONObject> identIterator = identifiers.iterator();
		while (identIterator.hasNext()) {
			String type = (String) identIterator.next().get("type");
			if(type.startsWith("ISBN")){
				String tempISBN = (String) identIterator.next().get("identifier");
				if(type.equals("ISBN_10")){
					isbn10 = tempISBN;
					isbn = ISBNUtils.validateAndReturn(isbn10);
				}else if(type.equals("ISBN_13")){
					isbn13 = tempISBN;
					isbn = ISBNUtils.validateAndReturn(isbn13);
				}else{
					System.out.println("Shit happend");
				}				
			}
		}
		
		pages = (Long) volumeInfo.get("pageCount");
		
		Book book = new Book(authorsArray, title, publisher, year, isbn, null, pages, null, null,null);
		
		return book;
	}
}