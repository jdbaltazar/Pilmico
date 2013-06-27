package common.entity.sales;

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
public class SalesDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sales_id")
	private Sales sales;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "price_per_kilo")
	private double pricePerKilo;

	@Column(name = "price_per_sack")
	private double pricePerSack;

	@Column(name = "quantity_per_kilo")
	private double quantityPerKilo;

	@Column(name = "quantity_per_sack")
	private double quantityPerSack;

	public SalesDetail() {
		super();
	}

	public SalesDetail(Sales sales, Product product, double pricePerKilo, double pricePerSack, double quantityPerKilo, double quantityPerSack) {
		super();
		this.sales = sales;
		this.product = product;
		this.pricePerKilo = pricePerKilo;
		this.pricePerSack = pricePerSack;
		this.quantityPerKilo = quantityPerKilo;
		this.quantityPerSack = quantityPerSack;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
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

	public double getQuantityPerKilo() {
		return quantityPerKilo;
	}

	public void setQuantityPerKilo(double quantityPerKilo) {
		this.quantityPerKilo = quantityPerKilo;
	}

	public double getQuantityPerSack() {
		return quantityPerSack;
	}

	public void setQuantityPerSack(double quantityPerSack) {
		this.quantityPerSack = quantityPerSack;
	}

	public String toString() {
		return id + "";
	}

}
