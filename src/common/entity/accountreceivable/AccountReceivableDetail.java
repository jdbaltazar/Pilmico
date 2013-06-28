package common.entity.accountreceivable;

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
public class AccountReceivableDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_receivable_id")
	private AccountReceivable accountReceivable;

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

	public AccountReceivableDetail() {
		super();
	}

	public AccountReceivableDetail(AccountReceivable accountReceivable, Product product, double pricePerKilo, double pricePerSack,
			double quantityPerKilo, double quantityPerSack) {
		super();
		this.accountReceivable = accountReceivable;
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

	public AccountReceivable getAccountReceivable() {
		return accountReceivable;
	}

	public void setAccountReceivable(AccountReceivable accountReceivable) {
		this.accountReceivable = accountReceivable;
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
