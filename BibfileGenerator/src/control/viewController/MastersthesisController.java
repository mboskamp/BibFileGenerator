package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_MASTERSTHESIS mastersthesis}.
 * 
 * @author Miklas Boskamp
 */
public class MastersthesisController extends AbstractAcademicDocumentController {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_MASTERSTHESIS;
	}
}
