package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	public TextField school;

	@FXML
	public TextField type;

	@FXML
	public TextField address;

	@Override
	public abstract void initialize();
}
