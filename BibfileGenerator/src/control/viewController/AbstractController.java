package control.viewController;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract superclass for any view controller.<br>
 * All controllers inherit this class.
 * 
 * @author Miklas Boskamp
 */
public abstract class AbstractController {

	@Getter
	@Setter
	public AbstractController from;
}
