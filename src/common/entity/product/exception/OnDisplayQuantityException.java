package common.entity.product.exception;

public class OnDisplayQuantityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6076854277411048098L;

	public OnDisplayQuantityException() {
		super("On display quantity cannot be greater than the total quantity");
	}

}
