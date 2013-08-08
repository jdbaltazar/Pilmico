package common.entity.product.exception;

public class NotEnoughQuantityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3874405065733278152L;

	public NotEnoughQuantityException() {
		super("Not enough quantity!");
	}

}
