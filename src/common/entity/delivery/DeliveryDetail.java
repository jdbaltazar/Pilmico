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
	@JoinColumn(name = "item_id")
	private Product item;

	@Column
	private double quantity;

	public DeliveryDetail() {
		super();
	}

	public DeliveryDetail(Delivery delivery, Product item, double quantity) {
		super();
		this.delivery = delivery;
		this.item = item;
		this.quantity = quantity;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

}
