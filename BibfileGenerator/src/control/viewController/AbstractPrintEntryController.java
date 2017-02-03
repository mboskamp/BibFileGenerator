package control.viewController;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Abstract superclass for any publication in printed form. <br>
 * The following controllers directly inherit the
 * AbstractConferenceController:<br>
 * <ul>
 * <li>{@link ArticleController}</li>
 * <li>{@link BookletController}</li>
 * <li>{@link MiscController}</li>
 * <li>{@link UnpublishedController}</li>
 * </ul>
 * The following <u>abstract</u> controllers inherit the
 * AbstractConferenceController:
 * <ul>
 * <li>{@link AbstractAcademicDocumentController}</li>
 * <li>{@link AbstractConferenceController}</li>
 * <li>{@link AbstractISBNController}</li>
 * <li>{@link AbstractTechnicalDocumentController}</li>
 * </ul>
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractPrintEntryController extends AbstractEntryController {

	@FXML
	public EntryTextField author;

	@Override
	public abstract void initialize();

}