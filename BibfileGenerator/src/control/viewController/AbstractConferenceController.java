package control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	public TextField booktitle;

	@FXML
	public TextField editor;

	/**
	 * volume/number
	 */
	@FXML
	public TextField volume;

	/**
	 * volume/number
	 */
	@FXML
	public TextField number;

	@FXML
	public TextField series;

	@FXML
	public TextField pages;

	@FXML
	public TextField address;

	@FXML
	public TextField organization;

	@FXML
	public TextField publisher;

	@Override
	public abstract void initialize();
}
