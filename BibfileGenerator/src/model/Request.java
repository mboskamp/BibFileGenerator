package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Request {
	
	private static byte[] HmacSHA256(String data, byte[] key) throws Exception  {
	     String algorithm="HmacSHA256";
	     Mac mac = Mac.getInstance(algorithm);
	     mac.init(new SecretKeySpec(key, algorithm));
	     return mac.doFinal(data.getBytes("UTF8"));
	}

	private static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception  {
	     byte[] kSecret = ("AWS4" + key).getBytes("UTF8");
	     byte[] kDate    = HmacSHA256(dateStamp, kSecret);
	     byte[] kRegion  = HmacSHA256(regionName, kDate);
	     byte[] kService = HmacSHA256(serviceName, kRegion);
	     byte[] kSigning = HmacSHA256("aws4_request", kService);
	     return kSigning;
	}
	
	
	public static void fireRequest(ISBN isbn){
		String tstmp = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS").format(new Date());
		System.out.println(tstmp);
	}
}
