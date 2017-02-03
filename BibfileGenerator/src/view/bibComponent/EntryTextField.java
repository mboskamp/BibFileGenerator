/**
 * 
 */
package view.bibComponent;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

/**
 * Extension from the normal {@link TextField} => An EntryTextField has a property required.
 * 
 * @author David Golla
 */
@Getter
@Setter
public class EntryTextField extends TextField {

	private boolean required;
		
	public EntryTextField() {
		this("");
	}

	public EntryTextField(String arg0) {
		super(arg0);
	}

}
