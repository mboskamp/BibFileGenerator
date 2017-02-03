package control.viewController;

import javafx.fxml.FXML;
import view.bibComponent.EntryTextField;

/**
 * Abstract superclass for any paper that is linked to conference papers.<br>
 * The following controllers inherit the AbstractConferenceController:<br>
 * <ul>
 * <li>{@link ConferenceController}</li>
 * <li>{@link InproceedingsController}</li>
 * </ul>
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractConferenceController extends AbstractPrintEntryController {

	@FXML
	public EntryTextField booktitle;

	@FXML
	public EntryTextField editor;

	/**
	 * volume/number
	 */
	@FXML
	public EntryTextField volume;

	/**
	 * volume/number
	 */
	@FXML
	public EntryTextField number;

	@FXML
	public EntryTextField series;

	@FXML
	public EntryTextField pages;

	@FXML
	public EntryTextField address;

	@FXML
	public EntryTextField organization;

	@FXML
	public EntryTextField publisher;

	@Override
	public abstract void initialize();
}
