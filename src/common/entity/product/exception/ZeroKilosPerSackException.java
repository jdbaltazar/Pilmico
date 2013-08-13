package common.entity.product.exception;

public class ZeroKilosPerSackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9199524980051890310L;

	public ZeroKilosPerSackException() {
		super("Kilos per sack cannot be zero!");
	}

}
