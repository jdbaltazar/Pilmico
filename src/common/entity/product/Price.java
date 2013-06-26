package common.entity.product;

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

@Entity
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "date_updated")
	@Temporal(TemporalType.TIMESTAMP)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Date dateUpdated;

	@Column(name = "price_per_sack")
	private double pricePerSack;

	@Column(name = "price_per_kilo")
	private double pricePerKilo;

	public Price() {
		super();
	}

	public Price(int id, Product product, Date dateUpdated, double pricePerSack, double pricePerKilo) {
		super();
		this.id = id;
		this.product = product;
		this.dateUpdated = dateUpdated;
		this.pricePerSack = pricePerSack;
		this.pricePerKilo = pricePerKilo;
	}

	public Price(int id, Product product, double pricePerSack, double pricePerKilo) {
		super();
		this.id = id;
		this.product = product;
		this.dateUpdated = new Date();
		this.pricePerSack = pricePerSack;
		this.pricePerKilo = pricePerKilo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
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

	public String toString() {
		return pricePerKilo + "/sack" + " and " + pricePerKilo + "/kilo";
	}

}
