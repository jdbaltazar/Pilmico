package common.entity.product.exception;

public class NegativeValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7826768765124912209L;

	public NegativeValueException() {
		super("Value cannot be negative!");
	}

}
