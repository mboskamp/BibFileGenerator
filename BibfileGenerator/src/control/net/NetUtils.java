package control.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import model.isbn.ISBN;

public class NetUtils {

	/**
	 * This method checks, if the runtime device has Internet access, by firing a request to google.com.
	 * @return <code>True</code> if google.com answered to the request and <code>false</code> if there was no answer.
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
	 * Taken from: Examples of How to Derive a Version 4 Signing Key - {@link http://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html#signature-v4-examples-java}
	 * @param key Private key
	 * @param dateStamp Datestamp in format: "yyyy-mm-dd'T'hh:mm:ss.SSS"
	 * @param regionName TODO:?
	 * @param serviceName http://docs.aws.amazon.com/AWSECommerceService/latest/DG/CHAP_OperationListAlphabetical.html
	 * @return the signature
	 * @throws Exception 
	 */
	public static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception  {
	     byte[] kSecret = ("AWS4" + key).getBytes("UTF8");
	     byte[] kDate    = HmacSHA256(dateStamp, kSecret);
	     byte[] kRegion  = HmacSHA256(regionName, kDate);
	     byte[] kService = HmacSHA256(serviceName, kRegion);
	     byte[] kSigning = HmacSHA256("aws4_request", kService);
	     return kSigning;
	}

	/**
	 * Taken from: Examples of How to Derive a Version 4 Signing Key - {@link http://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html#signature-v4-examples-java}
	 * @param data The data to be encrypted
	 * @param key Private key
	 * @return the encrypted bytes
	 * @throws Exception
	 */
	private static byte[] HmacSHA256(String data, byte[] key) throws Exception  {
		String algorithm="HmacSHA256";
		Mac mac = Mac.getInstance(algorithm);
		mac.init(new SecretKeySpec(key, algorithm));
		return mac.doFinal(data.getBytes("UTF8"));
	}
	
	/**
	 * Fires a request to the ISBN database. Returns the returned xml structure as string. (TODO)
	 * @param isbn the ISBN number to lookup in the database.
	 */
	public static void fireRequest(ISBN isbn){
		String tstmp = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS").format(new Date());
		System.out.println(tstmp);
	}
}
