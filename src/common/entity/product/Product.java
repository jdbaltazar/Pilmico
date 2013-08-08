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

import common.entity.product.exception.NegativeValueException;
import common.entity.product.exception.NotEnoughQuantityException;
import common.entity.product.exception.OnDisplayQuantityException;

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
	private double quantityOnDisplayInSack;

	@Column(name = "display_in_kilo")
	private double quantityOnDisplayInKilo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category")
	private Category category;

	@Column(name = "allow_alert")
	private boolean allowAllert;

	public Product() {
		super();
	}

	public Product(String name, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack, boolean available,
			double quantityInSack, double quantityInKilo, double quantityOnDisplayInSack, double quantityOnDisplayInKilo, Category category,
			boolean allowAllert) {
		super();
		this.name = name;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.quantityInSack = quantityInSack;
		this.quantityInKilo = quantityInKilo;
		this.quantityOnDisplayInSack = quantityOnDisplayInSack;
		this.quantityOnDisplayInKilo = quantityOnDisplayInKilo;
		this.category = category;
		this.allowAllert = allowAllert;
	}

	public Product(String name, String description, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack,
			boolean available, double quantityInSack, double quantityInKilo, double quantityOnDisplayInSack, double quantityOnDisplayInKilo,
			Category category, boolean allowAllert) {
		super();
		this.name = name;
		this.description = description;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.quantityInSack = quantityInSack;
		this.quantityInKilo = quantityInKilo;
		this.quantityOnDisplayInSack = quantityOnDisplayInSack;
		this.quantityOnDisplayInKilo = quantityOnDisplayInKilo;
		this.category = category;
		this.allowAllert = allowAllert;
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

	public void setKilosPerSack(double kilosPerSack) throws Exception {
		if (valid(kilosPerSack))
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

	public double getQuantityInKilo() {
		return quantityInKilo;
	}

	public double getTotalQuantityInSack() {
		return totalSacks(quantityInSack, quantityInKilo, kilosPerSack);
	}

	public double getTotalQuantityInKilo() {
		return totalKilos(quantityInSack, quantityInKilo, kilosPerSack);
	}

	public void setQuantity(double quantityInSack, double quantityInKilo) throws Exception {
		if (valid(quantityInSack) && valid(quantityInKilo)) {
			ProductQuantity pq = simplify(quantityInSack, quantityInKilo, kilosPerSack);
			this.quantityInSack = pq.getQuantityInSack();
			this.quantityInKilo = pq.getQuantityInKilo();
		}
	}

	public void incrementQuantity(double incrementQuantityInSack, double incrementQuantityInKilo) throws Exception {
		if (valid(incrementQuantityInSack) && valid(incrementQuantityInKilo)) {
			ProductQuantity pQuantity = computeIncrementResult(quantityInSack, quantityInKilo, kilosPerSack, incrementQuantityInSack,
					incrementQuantityInKilo);
			this.quantityInSack = pQuantity.getQuantityInSack();
			this.quantityInKilo = pQuantity.getQuantityInKilo();
		}
	}

	public void decrementQuantity(double decrementQuantityInSack, double decrementQuantityInKilo) throws Exception {
		if (valid(decrementQuantityInSack) && valid(decrementQuantityInKilo)) {
			ProductQuantity pQuantity = computeDecrementResult(quantityInSack, quantityInKilo, kilosPerSack, decrementQuantityInSack,
					decrementQuantityInKilo);
			this.quantityInSack = pQuantity.getQuantityInSack();
			this.quantityInKilo = pQuantity.getQuantityInKilo();
		}
	}

	public double getQuantityOnDisplayInSack() {
		return quantityOnDisplayInSack;
	}

	public double getQuantityOnDisplayInKilo() {
		return quantityOnDisplayInKilo;
	}

	public double getTotalQuantityOnDisplayInSack() {
		return totalSacks(quantityOnDisplayInSack, quantityOnDisplayInKilo, kilosPerSack);
	}

	public double getTotalQuantityOnDisplayInKilo() {
		return totalKilos(quantityOnDisplayInSack, quantityOnDisplayInKilo, kilosPerSack);
	}

	public void setQuantityOnDisplay(double quantityOnDisplayInSack, double quantityOnDisplayInKilo) throws Exception {
		if (valid(quantityOnDisplayInSack) && valid(quantityOnDisplayInKilo)) {
			double newKilosQuantity = totalKilos(quantityOnDisplayInSack, quantityOnDisplayInKilo, kilosPerSack);
			if (newKilosQuantity <= getTotalQuantityInKilo()) {
				ProductQuantity pQuantity = simplify(newKilosQuantity, kilosPerSack);
				this.quantityOnDisplayInSack = pQuantity.getQuantityInSack();
				this.quantityOnDisplayInKilo = pQuantity.getQuantityInKilo();
			} else {
				throw new OnDisplayQuantityException();
			}
		}
	}

	public boolean isOnDisplay() {
		return (quantityOnDisplayInSack != 0d || quantityOnDisplayInKilo != 0d);
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

	public String toString() {
		return name;
	}

	public static boolean valid(double amount) throws NegativeValueException {
		if (amount < 0d)
			throw new NegativeValueException();
		return true;
	}

	public static boolean validIncrement(double quantityInSack, double quantityInKilo, double kilosPerSack, double decrementQuantityInSack,
			double decrementQuantityInKilo) throws NegativeValueException {
		if (valid(decrementQuantityInSack) && valid(decrementQuantityInKilo)) {
			return true;
		}
		return false;
	}

	public static boolean validDecrement(double quantityInSack, double quantityInKilo, double kilosPerSack, double decrementQuantityInSack,
			double decrementQuantityInKilo) throws NegativeValueException, NotEnoughQuantityException {
		if (valid(decrementQuantityInSack) && valid(decrementQuantityInKilo)) {
			double kilosQuantity = totalKilos(quantityInSack, quantityInKilo, kilosPerSack);
			double decrementKilosQuantity = totalKilos(decrementQuantityInSack, decrementQuantityInKilo, kilosPerSack);
			if (decrementKilosQuantity > kilosQuantity)
				throw new NotEnoughQuantityException();
		}
		return true;
	}

	public static ProductQuantity computeIncrementResult(double quantityInSack, double quantityInKilo, double kilosPerSack,
			double incrementQuantityInSack, double incrementQuantityInKilo) throws NegativeValueException, NotEnoughQuantityException {
		ProductQuantity pQuantity = new ProductQuantity(quantityInSack, quantityInKilo);
		if (validIncrement(quantityInSack, quantityInKilo, kilosPerSack, incrementQuantityInSack, incrementQuantityInKilo)) {
			double kilosQuantity = totalKilos(quantityInSack, quantityInKilo, kilosPerSack);
			double incrementKilosQuantity = totalKilos(incrementQuantityInSack, incrementQuantityInKilo, kilosPerSack);
			kilosQuantity += incrementKilosQuantity;
			pQuantity = simplify(kilosQuantity, kilosPerSack);
		}
		return pQuantity;
	}

	public static ProductQuantity computeDecrementResult(double quantityInSack, double quantityInKilo, double kilosPerSack,
			double decrementQuantityInSack, double decrementQuantityInKilo) throws NegativeValueException, NotEnoughQuantityException {
		ProductQuantity pQuantity = new ProductQuantity(quantityInSack, quantityInKilo);
		if (validDecrement(quantityInSack, quantityInKilo, kilosPerSack, decrementQuantityInSack, decrementQuantityInKilo)) {
			double kilosQuantity = totalKilos(quantityInSack, quantityInKilo, kilosPerSack);
			double decrementKilosQuantity = totalKilos(decrementQuantityInSack, decrementQuantityInKilo, kilosPerSack);
			kilosQuantity -= decrementKilosQuantity;
			pQuantity = simplify(kilosQuantity, kilosPerSack);
		}
		return pQuantity;
	}

	public static double totalSacks(double sacks, double kilos, double klsPerSk) {
		return sacks + (kilos / klsPerSk);
	}

	public static double totalKilos(double sacks, double kilos, double klsPerSk) {
		return (sacks * klsPerSk) + kilos;
	}

	public static ProductQuantity simplify(double quantityInSack, double quantityInKilo, double kilosPerSack) throws NegativeValueException {
		if (valid(kilosPerSack) && valid(quantityInKilo) && valid(quantityInSack)) {
			double kilosQuantity = totalKilos(quantityInSack, quantityInKilo, kilosPerSack);
			double rawResult = kilosQuantity / kilosPerSack;
			// rounded two decimal result
			double roundedTwodecimalResult = Math.round(rawResult * 100.0d) / 100.0d;
			ProductQuantity pq = new ProductQuantity(Math.floor(roundedTwodecimalResult),
					((roundedTwodecimalResult - Math.floor(roundedTwodecimalResult)) * 100.0d) / 100.0d);
			return pq;
		} else {
			throw new NegativeValueException();
		}
	}

	public static ProductQuantity simplify(double totalQuantityInKilo, double kilosPerSack) throws NegativeValueException {
		if (valid(kilosPerSack) && valid(totalQuantityInKilo)) {
			double rawResult = totalQuantityInKilo / kilosPerSack;
			// rounded two decimal result
			double roundedTwodecimalResult = Math.round(rawResult * 100.0d) / 100.0d;
			ProductQuantity pq = new ProductQuantity(Math.floor(roundedTwodecimalResult),
					((roundedTwodecimalResult - Math.floor(roundedTwodecimalResult)) * 100.0d) / 100.0d);
			return pq;
		} else {
			throw new NegativeValueException();
		}
	}
}
