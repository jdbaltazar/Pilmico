package common.entity.product;

import common.entity.product.exception.NegativeValueException;

public class ProductQuantity {

	private double quantityInSack;
	private double quantityInKilo;
	private double kilosPerSack;

	public ProductQuantity() {
		super();
	}

	public ProductQuantity(double quantityInSack, double quantityInKilo, double kilosPerSack) {
		super();
		this.quantityInSack = quantityInSack;
		this.quantityInKilo = quantityInKilo;
		this.kilosPerSack = kilosPerSack;
	}

	public double getQuantityInSack() {
		return quantityInSack;
	}

	public boolean setQuantityInSack(double quantityInSack) throws NegativeValueException {
		if (quantityInSack < 0d)
			throw new NegativeValueException();
		this.quantityInSack = quantityInSack;
		return true;
	}

	public double getQuantityInKilo() {
		return quantityInKilo;
	}

	public boolean setQuantityInKilo(double quantityInKilo) throws NegativeValueException {
		if (quantityInKilo < 0d)
			throw new NegativeValueException();
		this.quantityInKilo = quantityInKilo;
		return true;
	}

	public double getKilosPerSack() {
		return kilosPerSack;
	}

	public void setKilosPerSack(double kilosPerSack) throws NegativeValueException {
		if (kilosPerSack < 0d)
			throw new NegativeValueException();
		this.kilosPerSack = kilosPerSack;
	}

}
