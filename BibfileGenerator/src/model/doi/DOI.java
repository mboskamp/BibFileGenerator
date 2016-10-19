package model.doi;

import control.doi.DOIFactory;
import lombok.Getter;
import lombok.Setter;

public class DOI {

	private final int PREFIX = 10;
	@Getter
	@Setter
	private String organization;
	@Getter
	@Setter
	private String identifier;

	/**
	 * Do not use. Use {@link DOIFactory#create(String)} instead as it validates the input.
	 */
	public DOI() {}

	@Override
	public String toString() {
		return PREFIX + "." + organization + "/" + identifier;
	}
}