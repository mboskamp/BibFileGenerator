/**
 * 
 */
package view.bibComponent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

/**
 * Extension from the normal {@link TextField} => An EntryTextField has a
 * property required.
 * 
 * @author David Golla
 */
public class EntryTextField extends TextField {

	@Getter
	@Setter
	private boolean required;

	@Getter
	private boolean changed;
	
	private String oldText = "";

	public EntryTextField() {
		this("");
	}

	public EntryTextField(String arg0) {
		super(arg0);
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if(!oldText.equals(getText())){
						changed = true;
						System.out.println("Changed from " + oldText + " to " + getText());
					}
				}else{
					changed = false;
					oldText = getText();
				}
			}
		});
	}

}
