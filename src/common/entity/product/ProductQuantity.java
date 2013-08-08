package common.entity.product;

import common.entity.product.exception.NegativeValueException;
import common.entity.product.exception.NotEnoughQuantityException;

public class ProductQuantity {

	private double quantityInSack;
	private double quantityInKilo;

	public ProductQuantity() {
		super();
	}

	public ProductQuantity(double quantityInSack, double quantityInKilo) {
		super();
		this.quantityInSack = quantityInSack;
		this.quantityInKilo = quantityInKilo;
	}

	public double getQuantityInSack() {
		return quantityInSack;
	}

	public boolean setQuantityInSack(double quantityInSack) throws NegativeValueException {
		if (Product.valid(quantityInSack)) {
			this.quantityInSack = quantityInSack;
			return true;
		}
		return false;
	}

	public double getQuantityInKilo() {
		return quantityInKilo;
	}

	public boolean setQuantityInKilo(double quantityInKilo) throws NegativeValueException {
		if (Product.valid(quantityInKilo)) {
			this.quantityInKilo = quantityInKilo;
			return true;
		}
		return false;
	}

}
