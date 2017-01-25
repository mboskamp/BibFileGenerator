package control.json;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import control.doi.DOIFactory;
import control.isbn.ISBNUtils;
import model.Author;
import model.Book;
import model.doi.DOI;
import model.isbn.ISBN;

public class JSONUtils {

	/**
	 * EXPERIMENTAL: not yet implemented and tested.<br>
	 * Parses the JSON input and extracts the necessary DOI data. The found article is returned.
	 * 
	 * @param json
	 *            The JSON input
	 * @return the found {@link DOI}
	 */
	public static DOI parseJSONDOIResponse(String json){
		String title;
		String magazine;
		String volume;
		String year;
		
		String number;
		String pages;
		String month;
		String[] months = {"January",      
				   "February",
				   "March",        
				   "April",        
				   "May",          
				   "June",         
				   "July",         
				   "August",       
				   "September",    
				   "October",      
				   "November",     
				   "December"};
		String doiString;
		
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(json);
			JSONObject jsonObj = (JSONObject) obj;
			
			JSONObject message = (JSONObject) jsonObj.get("message");
			
			JSONObject created = (JSONObject) message.get("created");
			JSONArray dateParts = (JSONArray) created.get("date-parts");
			JSONArray date = (JSONArray) dateParts.get(0);
			
			title = (String) ((JSONArray) message.get("title")).get(0);
			magazine = (String) message.get("publisher");
			volume = (String) message.get("volume");
			year = date.get(1)+"";
			
			number = (String) message.get("issue");
			pages = (String) message.get("page");
			month = months[Math.toIntExact((Long) date.get(1))-1];
			doiString = (String) message.get("DOI");
			DOI doi = DOIFactory.getInstance().create(doiString);
			doi.toString();
		} catch (ParseException e) {
			// TODO show error dialog
			e.printStackTrace();
		}
		
		//TODO new DOI
		return null;
	}

	/**
	 * Parses the JSON input and extracts the necessary book data. All found
	 * books are added to an ArrayList which will be returned.
	 * 
	 * @param json
	 *            The JSON input
	 * @return An ArrayList of {@link Book Books} or <code>Null</code> if no
	 *         book was found.
	 */
	public static ArrayList<Book> parseJSONBookResponse(String json) {
		JSONParser parser = new JSONParser();
		ArrayList<Book> books = new ArrayList<>();

		try {
			Object obj = parser.parse(json);

			JSONObject jsonObj = (JSONObject) obj;

			JSONArray items = (JSONArray) jsonObj.get("items");
			if (items != null) {
				Iterator<?> iterator = items.iterator();
				while (iterator.hasNext()) {
					books.add(parseBookItem((JSONObject) iterator.next()));
				}
			} else {
				System.out.println("ISBN not found");
			}

		} catch (ParseException e) {
			if (books.size() != 0) {
				return books;
			} else {
				//Error parsing JSON
				// TODO: Show error dialog
				e.printStackTrace();
				return null;
			}
		}
		return books;
	}

	/**
	 * Parses an individual JSONObject that represents a book. The data is
	 * extracted and a {@link Book} Object is returned.
	 * 
	 * @param json
	 *            The JSON-object that contains data about the book.
	 * @return The {@link Book}-object that now contains the passed data.
	 */
	private static Book parseBookItem(JSONObject json) {
		String title;
		ArrayList<Author> authorsList = new ArrayList<>();
		String publisher = null;
		String year = null;
		String isbn10 = "";
		String isbn13 = "";
		ISBN isbn = null;

		Long pages = null;

		URL url = null;
		BufferedImage image = null;

		JSONObject volumeInfo = (JSONObject) json.get("volumeInfo");
		title = (String) volumeInfo.get("title");
		publisher = (String) volumeInfo.get("publisher");
		year = (String) volumeInfo.get("publishedDate");

		JSONArray authorsJSON = (JSONArray) volumeInfo.get("authors");
		Iterator<?> authorIterator = authorsJSON.iterator();
		while (authorIterator.hasNext()) {
			String[] names = ((String) authorIterator.next()).split(" ");
			String name = names[names.length - 1].trim();
			String firstName = "";
			for (int i = 0; i < names.length - 1; i++) {
				firstName += names[i] + " ";
			}
			firstName = firstName.trim();
			authorsList.add(new Author(name, firstName));
		}
		Author[] authorsArray = new Author[authorsList.size()];
		authorsArray = authorsList.toArray(authorsArray);

		JSONArray identifiers = (JSONArray) volumeInfo.get("industryIdentifiers");
		Iterator<?> identIterator = identifiers.iterator();
		while (identIterator.hasNext()) {
			String type = (String) ((JSONObject) identIterator.next()).get("type");
			if (type.startsWith("ISBN")) {
				String tempISBN = (String) ((JSONObject) identIterator.next()).get("identifier");
				if (type.equals("ISBN_10")) {
					isbn10 = tempISBN;
					isbn = ISBNUtils.validateAndReturn(isbn10);
				} else if (type.equals("ISBN_13")) {
					isbn13 = tempISBN;
					isbn = ISBNUtils.validateAndReturn(isbn13);
				} else {
					System.out.println("Shit happend");
				}
			}
		}

		pages = (Long) volumeInfo.get("pageCount");

		try {
			JSONObject imageLinks = (JSONObject) volumeInfo.get("imageLinks");
			String urlString = (String) imageLinks.get("thumbnail");
			if (urlString == null) {
				urlString = (String) imageLinks.get("smallThumbnail");
			}
			url = new URL(urlString);
			image = ImageIO.read(url);
		} catch (MalformedURLException | RuntimeException e) {
			// No Image available. Do nothing
			System.out.println("No image available in book: " + title);
		} catch (IOException e) {
			// Invalid image. No image available. Do nothing
		}

		//TODO Umstellung auf BibTeXEntry
		Book book = new Book(authorsArray, title, publisher, year, isbn, null, pages, null, null, null, image);

		return book;
	}
}