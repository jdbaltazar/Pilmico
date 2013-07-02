package common.entity.discountissue;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import common.entity.inventorysheet.InventorySheet;
import common.entity.product.Product;
import common.entity.profile.Person;

@Entity
public class DiscountIssue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "person_id")
	private Person customer;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheet inventorySheet;

	public DiscountIssue() {
		super();
	}

	public DiscountIssue(Date date, Product product, double amount, Person customer, boolean valid, String remarks) {
		super();
		this.date = date;
		this.product = product;
		this.amount = amount;
		this.customer = customer;
		this.valid = valid;
		this.remarks = remarks;
	}

	public DiscountIssue(Date date, double amount, boolean valid) {
		super();
		this.date = date;
		this.amount = amount;
		this.valid = valid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public InventorySheet getInventorySheet() {
		return inventorySheet;
	}

	public void setInventorySheet(InventorySheet inventorySheet) {
		this.inventorySheet = inventorySheet;
	}

	public String toString() {
		return id + "";
	}
}
