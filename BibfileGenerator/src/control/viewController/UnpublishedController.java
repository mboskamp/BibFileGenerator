package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

/**
 * Controller that handles the input of user data about an {@link BibTeXEntry#TYPE_UNPUBLISHED unpublished} entry.
 * 
 * @author Miklas Boskamp
 */
public class UnpublishedController extends AbstractPrintEntryController {

	@Override
	public void initialize() {
		//do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_UNPUBLISHED;
	}
}