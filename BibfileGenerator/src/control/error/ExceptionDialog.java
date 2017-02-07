package control.error;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Class for an easy use with our ExceptionDialogs
 * 
 * @author dgolla
 *
 */
public class ExceptionDialog {

	private final static String ASK_DEVS_MESSAGE = "Bei Fragen wenden Sie sich bitte an die Entwickler.";
	
	private Exception exception  = null;
	
	private Error error;
	
	private String contentText = "";
	
	public ExceptionDialog(Error err, Exception ex) {
		exception = ex;
		error = err;
		if(err.getErrorMessage() != null){
			contentText = err.getErrorMessage();
		}
		showEcxeptionDialog();
	}
	
	public void showEcxeptionDialog() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Es ist ein Fehler aufgetreten. ErrorID: " + error.name());
		alert.setContentText(contentText == ""? ASK_DEVS_MESSAGE : contentText + "\n\n" + ASK_DEVS_MESSAGE);

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}	
}