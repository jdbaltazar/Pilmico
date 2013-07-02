package common.entity.delivery;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import common.entity.product.Product;

@Entity(name = "DeliveryDetail")
public class DeliveryDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "price_per_kilo")
	private double pricePerKilo;

	@Column(name = "price_per_sack")
	private double pricePerSack;

	@Column(name = "quantity_in_kilo")
	private double quantityInKilo;

	@Column(name = "quantity_in_sack")
	private double quantityPerSack;

	public DeliveryDetail() {
		super();
	}

	public DeliveryDetail(Delivery delivery, Product product, double pricePerKilo, double pricePerSack, double quantityInKilo, double quantityPerSack) {
		super();
		this.delivery = delivery;
		this.product = product;
		this.pricePerKilo = pricePerKilo;
		this.pricePerSack = pricePerSack;
		this.quantityInKilo = quantityInKilo;
		this.quantityPerSack = quantityPerSack;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPricePerKilo() {
		return pricePerKilo;
	}

	public void setPricePerKilo(double pricePerKilo) {
		this.pricePerKilo = pricePerKilo;
	}

	public double getPricePerSack() {
		return pricePerSack;
	}

	public void setPricePerSack(double pricePerSack) {
		this.pricePerSack = pricePerSack;
	}

	public double getQuantityInKilo() {
		return quantityInKilo;
	}

	public void setQuantityInKilo(double quantityInKilo) {
		this.quantityInKilo = quantityInKilo;
	}

	public double getQuantityInSack() {
		return quantityPerSack;
	}

	public void setQuantityInSack(double quantityPerSack) {
		this.quantityPerSack = quantityPerSack;
	}

	public String toString() {
		return id + "";
	}

}
