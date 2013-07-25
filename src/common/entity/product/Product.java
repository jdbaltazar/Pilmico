package common.entity.product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Price> prices = new HashSet<Price>();

	@Column(name = "kilos_per_sack")
	private double kilosPerSack;

	@Column
	private boolean available;

	@Column(name = "quantity_in_sack")
	private double quantityInSack;

	@Column(name = "quantity_in_kilo")
	private double quantityInKilo;

	@Column(name = "display_in_sack")
	private double displayInSack;

	@Column(name = "display_in_kilo")
	private double displayInKilo;

	@Column(name = "sold_today_in_sack")
	private double soldTodayInSack;

	@Column(name = "sold_today_in_kilo")
	private double soldTodayInKilo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category")
	private Category category;

	@Column(name = "allow_alert")
	private boolean allowAllert;

	@Column(name = "alert_using_sack")
	private boolean alertUsingSack;

	@Column(name = "alert_on_quantity")
	private double alertOnQuantity;

	public Product() {
		super();
	}

	public Product(String name, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack, boolean available,
			double quantityInSack, double quantityInKilo, double displayInSack, double displayInKilo, double soldTodayInSack, double soldTodayInKilo,
			Category category, boolean allowAllert, boolean alertUsingSack, double alertOnQuantity) {
		super();
		this.name = name;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.quantityInSack = quantityInSack;
		this.quantityInKilo = quantityInKilo;
		this.displayInSack = displayInSack;
		this.displayInKilo = displayInKilo;
		this.soldTodayInSack = soldTodayInSack;
		this.soldTodayInKilo = soldTodayInKilo;
		this.category = category;
		this.allowAllert = allowAllert;
		this.alertUsingSack = alertUsingSack;
		this.alertOnQuantity = alertOnQuantity;
	}

	public Product(String name, String description, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack,
			boolean available, double quantityInSack, double quantityInKilo, double displayInSack, double displayInKilo, double soldTodayInSack,
			double soldTodayInKilo, Category category, boolean allowAllert, boolean alertUsingSack, double alertOnQuantity) {
		super();
		this.name = name;
		this.description = description;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.quantityInSack = quantityInSack;
		this.quantityInKilo = quantityInKilo;
		this.displayInSack = displayInSack;
		this.displayInKilo = displayInKilo;
		this.soldTodayInSack = soldTodayInSack;
		this.soldTodayInKilo = soldTodayInKilo;
		this.category = category;
		this.allowAllert = allowAllert;
		this.alertUsingSack = alertUsingSack;
		this.alertOnQuantity = alertOnQuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Price> getPriceHistory() {
		return prices;
	}

	public void setPriceHistory(Set<Price> prices) {
		this.prices = prices;
	}

	public void addPrice(Price price) {
		prices.add(price);
	}

	public Price getCurrentPrice() {
		Price currentPrice = null;
		for (Price p : prices) {
			if (currentPrice == null)
				currentPrice = p;
			else {
				if (p.getDateUpdated().after(currentPrice.getDateUpdated()))
					currentPrice = p;
			}
		}
		return currentPrice;
	}

	public double getCurrentPricePerSack() {
		Price p = getCurrentPrice();
		if (p != null)
			return getCurrentPrice().getPricePerSack();
		return 0;
	}

	public double getCurrentPricePerKilo() {
		Price p = getCurrentPrice();
		if (p != null)
			return getCurrentPrice().getPricePerKilo();
		return 0;
	}

	public double getKilosPerSack() {
		return kilosPerSack;
	}

	public void setKilosPerSack(double kilosPerSack) {
		this.kilosPerSack = kilosPerSack;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getQuantityInSack() {
		return quantityInSack;
	}

	public void setQuantityInSack(double quantityInSack) {
		this.quantityInSack = quantityInSack;
	}

	public double getQuantityInKilo() {
		return quantityInKilo;
	}

	public void setQuantityInKilo(double quantityInKilo) {
		this.quantityInKilo = quantityInKilo;
	}

	public double getDisplayInSack() {
		return displayInSack;
	}

	public void setDisplayInSack(double displayInSack) {
		this.displayInSack = displayInSack;
	}

	public double getDisplayInKilo() {
		return displayInKilo;
	}

	public void setDisplayInKilo(double displayInKilo) {
		this.displayInKilo = displayInKilo;
	}

	public double getSoldTodayInSack() {
		return soldTodayInSack;
	}

	public void setSoldTodayInSack(double soldTodayInSack) {
		this.soldTodayInSack = soldTodayInSack;
	}

	public double getSoldTodayInKilo() {
		return soldTodayInKilo;
	}

	public void setSoldTodayInKilo(double soldTodayInKilo) {
		this.soldTodayInKilo = soldTodayInKilo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isAllowAllert() {
		return allowAllert;
	}

	public void setAllowAllert(boolean allowAllert) {
		this.allowAllert = allowAllert;
	}

	public boolean alertUsingSack() {
		return alertUsingSack;
	}

	public void setAlertUsingSack(boolean alertUsingSack) {
		this.alertUsingSack = alertUsingSack;
	}

	public double getAlertOnQuantity() {
		return alertOnQuantity;
	}

	public void setAlertOnQuantity(double alertOnQuantity) {
		this.alertOnQuantity = alertOnQuantity;
	}

	public String toString() {
		return name;
	}

}
