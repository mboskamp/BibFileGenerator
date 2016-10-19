package control.doi;

import java.util.HashMap;
import java.util.Map;

import model.doi.DOI;

/**
 * 
 * @author Miklas Boskamp
 */
public class DOIFactory {
	
	private static DOIFactory INSTANCE = null;
	
	private static String VALID = "valid";
	private static String PREFIX = "prefix";
	private static String IDENTIFIER = "identifier";

	/**
	 * The singleton pattern does not allow a public constructor.
	 */
	private DOIFactory() {}
	
	/**
	 * Returns the current instance of this class. There can only be one
	 * instance at a time. If there is already one instance of this class, this
	 * instance will be returned.
	 * 
	 * @return The current instance of this class.
	 */
	public static DOIFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DOIFactory();
		}
		return INSTANCE;
	}
	
	public DOI create(String doi){
		Map<String, Object> result = validate(doi);
		if((Boolean) result.get("valid")){
			DOI tempDOI = new DOI();
			tempDOI.setIdentifier((String) result.get(IDENTIFIER));
			tempDOI.setOrganization(((String[]) result.get(PREFIX))[1]);
			return tempDOI;
		}else{
			return null;
		}
	}
	/**
	 * TODO
	 * @param doi
	 * @return
	 */
	//The prefix is 10.x where x is 4-5 digits. The suffix can be anything, but can't be left off.
	private Map<String, Object> validate(String doi){
		Map<String, Object> returnMap = new HashMap<>();
		boolean isValid = true;
		String[] prefix = {"10",""};
		String identifier = null;
		
		if(doi.substring(0, 3).equals("10.")){
			String org = doi.substring(3, doi.indexOf("/"));
			if(org.length() == 4 || org.length() == 5 && org.matches("[0-9]+")){
				prefix[1] = org;
				identifier = doi.substring(doi.indexOf("/") + 1);
			}else{
				System.out.println("malformed organization identifier");
				isValid = false;
			}
		}else{
			System.out.println("Doi does not start with '10.'");
			isValid = false;
		}
		
		returnMap.put(VALID, isValid);
		returnMap.put(PREFIX, prefix);
		returnMap.put(IDENTIFIER, identifier);
		return returnMap;
	}
}