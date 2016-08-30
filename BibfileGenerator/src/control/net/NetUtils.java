package control.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import model.isbn.ISBN;

public class NetUtils {

	private static String defaultRegion = "eu-central-1";
	private static String defaultService = "ItemLookup";

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
	 * Taken from: Examples of How to Derive a Version 4 Signing Key -
	 * {@link http://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html#signature-v4-examples-java}
	 * 
	 * @param key
	 *            Private key
	 * @param dateStamp
	 *            Datestamp in format: "yyyy-mm-dd'T'hh:mm:ss.SSS". Can be null
	 * @param regionName
	 *            See
	 *            http://docs.aws.amazon.com/general/latest/gr/rande.html#apigateway_region.
	 *            Can be null
	 * @param serviceName
	 *            http://docs.aws.amazon.com/AWSECommerceService/latest/DG/CHAP_OperationListAlphabetical.html
	 * @return the signature
	 * @throws Exception
	 */
	public static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName)
			throws Exception {
		byte[] kSecret = ("AWS4" + key).getBytes("UTF8");
		byte[] kDate = HmacSHA256(dateStamp, kSecret);
		byte[] kRegion = HmacSHA256(regionName == null ? defaultRegion : regionName, kDate);
		byte[] kService = HmacSHA256(serviceName == null ? defaultService : serviceName, kRegion);
		byte[] kSigning = HmacSHA256("aws4_request", kService);
		return kSigning;
	}

	/**
	 * Taken from: Examples of How to Derive a Version 4 Signing Key -
	 * {@link http://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html#signature-v4-examples-java}
	 * 
	 * @param data
	 *            The data to be encrypted
	 * @param key
	 *            Private key
	 * @return the encrypted bytes
	 * @throws Exception
	 */
	private static byte[] HmacSHA256(String data, byte[] key) throws Exception {
		String algorithm = "HmacSHA256";
		Mac mac = Mac.getInstance(algorithm);
		mac.init(new SecretKeySpec(key, algorithm));
		return mac.doFinal(data.getBytes("UTF8"));
	}

	/**
	 * Fires a request to the ISBN database. Returns the returned JSON structure
	 * as string.
	 * 
	 * @param isbn
	 *            the ISBN number to lookup in the database.
	 */
	public static void fireRequest(ISBN isbn) {
		//String tstmp = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS").format(new Date());

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
