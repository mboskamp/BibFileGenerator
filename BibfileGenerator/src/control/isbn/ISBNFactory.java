package control.isbn;

import model.ISBN;

public class ISBNFactory {
	private static ISBNFactory INSTANCE = null;
	
	private ISBNFactory() {}
	
	public static ISBNFactory getInstance() {
        if(INSTANCE==null){
        	INSTANCE = new ISBNFactory();
            }
        return INSTANCE;
	}
	
	public ISBN getISBN(String isbn){
		return ISBNUtils.validateAndReturn(isbn);
	}
}