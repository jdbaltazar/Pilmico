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
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sales_id")
	private Sales sales;

	@Column
	private int quantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "item_id")
	private Product item;

	public SalesDetail() {
		super();
	}

	public SalesDetail(Sales sales, int quantity, Product item) {
		super();
		this.sales = sales;
		this.quantity = quantity;
		this.item = item;
	}

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}

}
