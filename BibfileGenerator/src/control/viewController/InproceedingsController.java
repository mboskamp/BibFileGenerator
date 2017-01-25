package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

/**
 * Controller that handles the input of user data about an {@link BibTeXEntry#TYPE_INPROCEEDINGS inproceedings}.
 * 
 * @author Miklas Boskamp
 */
public class InproceedingsController extends AbstractConferenceController {

	@Override
	public void initialize() {
		// Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_INPROCEEDINGS;
	}
}
