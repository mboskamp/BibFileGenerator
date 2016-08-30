package control.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import model.isbn.ISBN;

/**
 * Provides static access to network functionalities.
 * 
 * @author Miklas Boskamp
 */
public class NetUtils {

	/**
	 * This method checks, if the runtime device has Internet access, by firing
	 * a request to google.com.
	 * 
	 * @return <code>True</code> if google.com answered to the request and
	 *         <code>false</code> if there was no answer.
	 */
	public static boolean netIsAvailable() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			return true;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Fires a request to the ISBN database. Returns the returned JSON structure
	 * as string.
	 * 
	 * @param isbn
	 *            the ISBN number to lookup in the database.
	 */
	public static void fireRequest(ISBN isbn) {
		String url = "https://www.googleapis.com/books/v1/volumes";
		Charset charset = java.nio.charset.StandardCharsets.UTF_8;
		String paramName = "q";
		String param = "ISBN";

		String query = "";

		try {
			query = String.format(paramName + "=%s:%s", URLEncoder.encode(param, charset.toString()), isbn.toString());
			URLConnection connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset.toString());
			InputStream response = connection.getInputStream();
			
			Scanner scanner = new Scanner(response);
			String responseBody = scanner.useDelimiter("\\A").next();
			System.out.println(responseBody);
			scanner.close();
		} catch (UnsupportedEncodingException e) {
			//Should not happen, as the encoding is standard UTF-8
			System.out.println("Unsupported encoding");
		} catch (MalformedURLException e) {
			System.out.println("Request URL is malformed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
