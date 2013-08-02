package common.entity.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
	private double quantityOnStockInSack;

	@Column(name = "quantity_in_kilo")
	private double quantityOnStockInKilo;

	@Column(name = "display_in_sack")
	private double quantityOnDisplayInSack;

	@Column(name = "display_in_kilo")
	private double quantityOnDisplayInKilo;

	@Column(name = "sold_today_in_sack")
	private double quantitySoldTodayInSack;

	@Column(name = "sold_today_in_kilo")
	private double quantitySoldTodayInKilo;

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
			double quantityOnStockInSack, double quantityOnStockInKilo, double quantityOnDisplayInSack, double quantityOnDisplayInKilo,
			double quantitySoldTodayInSack, double quantitySoldTodayInKilo, Category category, boolean allowAllert, boolean alertUsingSack,
			double alertOnQuantity) {
		super();
		this.name = name;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.quantityOnStockInSack = quantityOnStockInSack;
		this.quantityOnStockInKilo = quantityOnStockInKilo;
		this.quantityOnDisplayInSack = quantityOnDisplayInSack;
		this.quantityOnDisplayInKilo = quantityOnDisplayInKilo;
		this.quantitySoldTodayInSack = quantitySoldTodayInSack;
		this.quantitySoldTodayInKilo = quantitySoldTodayInKilo;
		this.category = category;
		this.allowAllert = allowAllert;
		this.alertUsingSack = alertUsingSack;
		this.alertOnQuantity = alertOnQuantity;
	}

	public Product(String name, String description, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack,
			boolean available, double quantityOnStockInSack, double quantityOnStockInKilo, double quantityOnDisplayInSack,
			double quantityOnDisplayInKilo, double quantitySoldTodayInSack, double quantitySoldTodayInKilo, Category category, boolean allowAllert,
			boolean alertUsingSack, double alertOnQuantity) {
		super();
		this.name = name;
		this.description = description;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.quantityOnStockInSack = quantityOnStockInSack;
		this.quantityOnStockInKilo = quantityOnStockInKilo;
		this.quantityOnDisplayInSack = quantityOnDisplayInSack;
		this.quantityOnDisplayInKilo = quantityOnDisplayInKilo;
		this.quantitySoldTodayInSack = quantitySoldTodayInSack;
		this.quantitySoldTodayInKilo = quantitySoldTodayInKilo;
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

	public List<Price> getPriceHistory() {
		return orderPriceHistory(copyPrices(prices));
	}

	private List<Price> copyPrices(Set<Price> orig) {
		List<Price> copy = new ArrayList<Price>();
		for (Price p : orig) {
			Price p2 = new Price(p.getProduct(), p.getDateUpdated(), p.getPricePerSack(), p.getPricePerKilo());
			p2.setId(p.getId());
			copy.add(p2);
		}
		return copy;
	}

	private List<Price> orderPriceHistory(List<Price> prices) {
		List<Price> ordered = new ArrayList<Price>();
		int origSize = prices.size();
		for (int i = 0; i < origSize; i++) {
			Price temp = getMostRecentPriceIn(prices);
			ordered.add(temp);
			prices.remove(prices.indexOf(temp));
		}
		return ordered;
	}

	private Price getMostRecentPriceIn(List<Price> prices) {
		Price mostRecent = null;
		for (Price p : prices) {
			if (mostRecent == null)
				mostRecent = p;
			else {
				if (p.getDateUpdated().after(mostRecent.getDateUpdated()))
					mostRecent = p;
			}
		}
		return mostRecent;
	}

	// public void setPriceHistory(Set<Price> prices) {
	// this.prices = prices;
	// }

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
		return quantityOnStockInSack + quantityOnDisplayInSack;
	}

	// public void setQuantityInSack2(double totalQuantityInSack) {
	// this.quantityOnDisplayInSack = quantityOnDisplayInSack;
	// }

	public void incrementQuantityInSack(double incrementQuantityInSack) throws Exception {
		if (incrementQuantityInSack < 0)
			throw new Exception("Cannot increment sack qty of product: " + id + " /nIncrement value cannot be negative");
		this.quantityOnStockInSack += incrementQuantityInSack;
	}

	public void decrementQuantityInSack(double decrementQuantityInSack) throws Exception {
		if (decrementQuantityInSack > getQuantityInSack())
			throw new Exception("Cannot decrement sack qty of product: " + id + " /nDecrement value cannot be greater than quantity");
		if (decrementQuantityInSack > quantityOnStockInSack) {
			decrementQuantityInSack -= quantityOnStockInSack;
			quantityOnDisplayInSack -= decrementQuantityInSack;
			quantityOnStockInSack = 0d;
		} else {
			quantityOnStockInSack -= decrementQuantityInSack;
		}

	}

	public void setQuantityInSack(double totalQuantityInSack, double quantityOnDisplayInSack) {
		this.quantityOnDisplayInSack = quantityOnDisplayInSack;
		if ((totalQuantityInSack - quantityOnStockInSack > 0))
			this.quantityOnStockInSack = totalQuantityInSack - quantityOnStockInSack;
	}

	public double getQuantityInKilo() {
		return quantityOnStockInKilo + quantityOnDisplayInKilo;
	}

	public void incrementQuantityInKilo(double incrementQuantityInKilo) throws Exception {
		if (incrementQuantityInKilo < 0)
			throw new Exception("Cannot increment kilo qty of product: " + id + " /nIncrement value cannot be negative");
		this.quantityOnStockInKilo += incrementQuantityInKilo;
	}

	public void decrementQuantityInKilo(double decrementQuantityInKilo) throws Exception {
		if (decrementQuantityInKilo > getQuantityInKilo())
			throw new Exception("Cannot decrement kilo qty of product: " + id + " /nDecrement value cannot be greater than quantity");
		if (decrementQuantityInKilo > quantityOnStockInKilo) {
			decrementQuantityInKilo -= quantityOnStockInKilo;
			quantityOnDisplayInKilo -= decrementQuantityInKilo;
			quantityOnStockInKilo = 0d;
		} else {
			quantityOnStockInKilo -= decrementQuantityInKilo;
		}

	}

	public void setQuantityInKilo(double totalQuantityInKilo, double quantityOnDisplayInKilo) {
		this.quantityOnDisplayInKilo = quantityOnDisplayInKilo;
		if ((totalQuantityInKilo - quantityOnStockInKilo > 0))
			this.quantityOnStockInKilo = totalQuantityInKilo - quantityOnStockInKilo;
	}

	public boolean validQuantityInSackResult(double quantityInSackToBeSubtracted) {
		if ((getQuantityInSack() - quantityInSackToBeSubtracted) >= 0)
			return true;
		return false;
	}

	public boolean validQuantityInKiloResult(double quantityInKiloToBeSubtracted) {
		if ((getQuantityInKilo() - quantityInKiloToBeSubtracted) >= 0)
			return true;
		return false;
	}

	public boolean validQuantityResult(double quantityInSackToBeSubtracted, double quantityInKiloToBeSubtracted) {
		return validQuantityInSackResult(quantityInSackToBeSubtracted) && validQuantityInKiloResult(quantityInKiloToBeSubtracted);
	}

	public double getQuantityOnDisplayInSack() {
		return quantityOnDisplayInSack;
	}

	public void setQuantityOnDisplayInSack(double quantityOnDisplayInSack) {
		this.quantityOnDisplayInSack = quantityOnDisplayInSack;
	}

	public double getQuantityOnDisplayInKilo() {
		return quantityOnDisplayInKilo;
	}

	public boolean isOnDisplay() {
		return (quantityOnDisplayInSack != 0d || quantityOnDisplayInKilo != 0d);
	}

	public void setQuantityOnDisplayInKilo(double quantityOnDisplayInKilo) {
		this.quantityOnDisplayInKilo = quantityOnDisplayInKilo;
	}

	public double getQuantitySoldTodayInSack() {
		return quantitySoldTodayInSack;
	}

	public void setQuantitySoldTodayInSack(double quantitySoldTodayInSack) {
		this.quantitySoldTodayInSack = quantitySoldTodayInSack;
	}

	public double getQuantitySoldTodayInKilo() {
		return quantitySoldTodayInKilo;
	}

	public void setQuantitySoldTodayInKilo(double quantitySoldTodayInKilo) {
		this.quantitySoldTodayInKilo = quantitySoldTodayInKilo;
	}

	public double getBeginningInventoryInSack() {
		return getQuantityInSack() + quantitySoldTodayInSack;
	}

	public double getBeginningInventoryInKilo() {
		return getQuantityInKilo() + quantitySoldTodayInKilo;
	}

	public void resetBeginningInventory() {
		quantitySoldTodayInSack = 0d;
		quantitySoldTodayInKilo = 0d;
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
