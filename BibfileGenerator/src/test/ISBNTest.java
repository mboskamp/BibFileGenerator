package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import control.isbn.ISBNUtils;
import model.isbn.ISBN;

public class ISBNTest {
	public ISBNTest() {
		InputStream in = getClass().getClassLoader().getResourceAsStream("isbn_test_cases");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		String[] modes = {"10_valid_clean", "10_valid_dirty", "10_invalid_clean", "13_valid_clean", "13_valid_dirty", "13_invalid_clean"};
		int modecount = 0;
		try {
			while ( (line = reader.readLine()) != null) {
			    if(!line.equals("")){
			    	try {
			    		ISBN isbn = ISBNUtils.validateAndReturn(line);
			    		System.out.println("read valid isbn: " + isbn.toFormattedString());
					} catch (RuntimeException e) {
						if(modes[modecount].contains("invalid")){
							System.out.println("read invalid isbn: " + line);
						}
					}
			    }else{
			    	modecount++;
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}