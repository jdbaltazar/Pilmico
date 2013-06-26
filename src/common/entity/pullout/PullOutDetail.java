package common.entity.pullout;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import common.entity.product.Product;

@Entity
public class PullOutDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "pullout_id")
	private PullOut pullOut;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product item;

	@Column(name = "quantity_in_kilo")
	private double quantityInKilo;

	public PullOutDetail() {
		super();
	}

	public PullOutDetail(PullOut pullOut, double quantityInKilo) {
		super();
		this.pullOut = pullOut;
		this.quantityInKilo = quantityInKilo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PullOut getPullOut() {
		return pullOut;
	}

	public void setPullOut(PullOut pullOut) {
		this.pullOut = pullOut;
	}

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}

	public double getQuantityInKilo() {
		return quantityInKilo;
	}

	public void setQuantityInKilo(double quantityInKilo) {
		this.quantityInKilo = quantityInKilo;
	}

}
