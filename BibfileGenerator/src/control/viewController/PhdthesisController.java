package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

/**
 * Controller that handles the input of user data about a {@link BibTeXEntry#TYPE_PHDTHESIS phdthesis}.
 * 
 * @author Miklas Boskamp
 */
public class PhdthesisController extends AbstractAcademicDocumentController {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_PHDTHESIS;
	}
}
