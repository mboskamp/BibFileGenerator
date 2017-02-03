package control.viewController;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Abstract superclass for any academic or scientific paper.<br>
 * The following controllers inherit the AbstractAcademicDocumentController:<br>
 * <ul>
 * <li>{@link MastersthesisController}</li>
 * <li>{@link PhdthesisController}</li>
 * </ul>
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractAcademicDocumentController extends AbstractPrintEntryController {

	@FXML
	public EntryTextField school;

	@FXML
	public EntryTextField type;

	@FXML
	public EntryTextField address;

	@Override
	public abstract void initialize();
}
