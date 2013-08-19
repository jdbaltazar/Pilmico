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
import common.entity.product.exception.ZeroKilosPerSackException;

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
	private double sacks;

	@Column(name = "quantity_in_kilo")
	private double kilosOnDisplay;

	// @Column(name = "display_in_sack")
	// private double quantityOnDisplayInSack;
	//
	// @Column(name = "display_in_kilo")
	// private double quantityOnDisplayInKilo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category")
	private Category category;

	@Column(name = "allow_alert")
	private boolean allowAllert;

	public Product() {
		super();
	}

	public Product(String name, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack, boolean available, double sacks,
			double kilosOnDisplay, Category category, boolean allowAllert) {
		super();
		this.name = name;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.sacks = sacks;
		this.kilosOnDisplay = kilosOnDisplay;
		this.category = category;
		this.allowAllert = allowAllert;
	}

	public Product(String name, String description, Date dateUpdated, double pricePerSack, double pricePerKilo, double kilosPerSack,
			boolean available, double sacks, double kilosOnDisplay, Category category, boolean allowAllert) {
		super();
		this.name = name;
		this.description = description;
		prices.add(new Price(this, dateUpdated, pricePerSack, pricePerKilo));
		this.kilosPerSack = kilosPerSack;
		this.available = available;
		this.sacks = sacks;
		this.kilosOnDisplay = kilosOnDisplay;
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

	public String getKilosPerSackDescription() {
		String s = "";
		if (kilosPerSack == 0d) {
			s = "0";
		} else {

			// check if has no decimal portions
			if ((kilosPerSack * 100) % 100 != 0) {
				s = String.format("%.2f", kilosPerSack);
			} else {
				s = (int) kilosPerSack + "";
			}
		}
		return s;
	}

	public void setKilosPerSack(double kilosPerSack) throws Exception {
		if (kilosPerSack < 0d)
			throw new NegativeValueException();
		this.kilosPerSack = kilosPerSack;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getSacks() {
		return sacks;
	}

	public String getSacksDescription() {
		String s = "";
		if (sacks == 0d) {
			s = "0";
		} else {

			// check if has no decimal portions
			if ((sacks * 100) % 100 != 0) {
				s = String.format("%.2f", sacks);
			} else {
				s = (int) sacks + "";
			}
		}
		return s;
	}

	public String getKilosOnDisplayDescription() {
		String s = "";
		if (kilosOnDisplay == 0d) {
			s = "0";
		} else {

			// check if has no decimal portions
			if ((kilosOnDisplay * 100) % 100 != 0) {
				s = String.format("%.2f", kilosOnDisplay);
			} else {
				s = (int) kilosOnDisplay + "";
			}
		}
		return s;
	}

	public double getKilosOnDisplay() {
		return kilosOnDisplay;
	}

	public double getTotalQuantityInSack() {
		return totalSacks(sacks, kilosOnDisplay, kilosPerSack);
	}

	public double getTotalQuantityInKilo() {
		return totalKilos(sacks, kilosOnDisplay, kilosPerSack);
	}

	public String getQuantityDescription() {

		String desc = "";
		if (getTotalQuantityInKilo() == 0d) {

			desc = "0 kilos remaining";

		} else {

			if (sacks > 0d) {
				desc = sacks + " sack/s";
				if (kilosOnDisplay > 0d)
					desc = desc + " and ";
			}

			if (kilosOnDisplay > 0d) {
				desc = desc + kilosOnDisplay + " kilo/s";
			}

			desc = desc + " remaining";
		}

		return desc;
	}

	public String getSimplifiedQuantity() {

		String desc = "";
		if (getTotalQuantityInKilo() == 0d) {
			desc = "0";
		} else {
			String s1 = "0";
			String s2 = "0";

			// check if has no decimal portions
			if ((sacks * 100) % 100 != 0) {
				s1 = String.format("%.2f", sacks);
			} else {
				s1 = (int) sacks + "";
			}

			if ((kilosOnDisplay * 100) % 100 != 0) {
				s2 = String.format("%.2f", kilosOnDisplay);
			} else {
				s2 = (int) kilosOnDisplay + "";
			}

			desc = s1 + ", " + s2;

		}

		return desc;
	}

	public void setQuantity(double sacks, double kilosOnDisplay) throws Exception {
		if (valid(sacks, kilosOnDisplay, kilosPerSack)) {
			this.sacks = sacks;
			this.kilosOnDisplay = kilosOnDisplay;
		}
	}

	public void incrementQuantity(double sacksIncrement, double kilosOnDisplayIncrement) throws Exception {
		if (valid(sacksIncrement, kilosOnDisplayIncrement, kilosPerSack)) {
			ProductQuantity pQuantity = computeIncrementResult(sacks, kilosOnDisplay, kilosPerSack, sacksIncrement, kilosOnDisplayIncrement);
			this.sacks = pQuantity.getQuantityInSack();
			this.kilosOnDisplay = pQuantity.getQuantityInKilo();
		}
	}

	public void decrementQuantity(double decrementQuantityInSack, double decrementQuantityInKilo) throws Exception {
		if (valid(decrementQuantityInSack, decrementQuantityInKilo, kilosPerSack)) {
			ProductQuantity pQuantity = computeDecrementResult(sacks, kilosOnDisplay, kilosPerSack, decrementQuantityInSack, decrementQuantityInKilo);
			this.sacks = pQuantity.getQuantityInSack();
			this.kilosOnDisplay = pQuantity.getQuantityInKilo();
		}
	}

	// public double getQuantityOnDisplayInSack() {
	// return quantityOnDisplayInSack;
	// }
	//
	// public double getQuantityOnDisplayInKilo() {
	// return quantityOnDisplayInKilo;
	// }
	//
	// public double getTotalQuantityOnDisplayInSack() {
	// return totalSacks(quantityOnDisplayInSack, quantityOnDisplayInKilo,
	// kilosPerSack);
	// }
	//
	// public double getTotalQuantityOnDisplayInKilo() {
	// return totalKilos(quantityOnDisplayInSack, quantityOnDisplayInKilo,
	// kilosPerSack);
	// }

	// public void setQuantityOnDisplay(double quantityOnDisplayInSack, double
	// quantityOnDisplayInKilo) throws Exception {
	// if (valid(quantityOnDisplayInSack, quantityOnDisplayInKilo, kilosPerSack))
	// {
	// double newKilosQuantity = totalKilos(quantityOnDisplayInSack,
	// quantityOnDisplayInKilo, kilosPerSack);
	// if (newKilosQuantity <= getTotalQuantityInKilo()) {
	// ProductQuantity pQuantity = simplify(newKilosQuantity, kilosPerSack);
	// this.quantityOnDisplayInSack = pQuantity.getQuantityInSack();
	// this.quantityOnDisplayInKilo = pQuantity.getQuantityInKilo();
	// } else {
	// throw new OnDisplayQuantityException();
	// }
	// }
	// }
	//
	public boolean isOnDisplay() {
		return (kilosOnDisplay != 0d);
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

	public static boolean valid(double sacks, double kilos, double kilosPerSack) throws NegativeValueException, ZeroKilosPerSackException {
		if (sacks < 0d || kilos < 0d)
			throw new NegativeValueException();
		if (kilosPerSack <= 0d)
			throw new ZeroKilosPerSackException();
		return true;
	}

	public static boolean validIncrement(double sacks, double kilosOnDisplay, double kilosPerSack, double sacksIncrement,
			double kilosOnDisplayIncrement) throws NegativeValueException, ZeroKilosPerSackException {
		if (valid(sacksIncrement, kilosOnDisplayIncrement, kilosPerSack)) {
			return true;
		}
		return false;
	}

	public static boolean validDecrement(double sacks, double kilosOnDisplay, double kilosPerSack, double sacksDecrement,
			double kilosOnDisplayDecrement) throws NegativeValueException, NotEnoughQuantityException, ZeroKilosPerSackException {
		if (valid(sacksDecrement, kilosOnDisplayDecrement, kilosPerSack)) {
			double kilosQuantity = totalKilos(sacks, kilosOnDisplay, kilosPerSack);
			double decrementKilosQuantity = totalKilos(sacksDecrement, kilosOnDisplayDecrement, kilosPerSack);
			if (decrementKilosQuantity > kilosQuantity)
				throw new NotEnoughQuantityException();
		}
		return true;
	}

	public static ProductQuantity computeIncrementResult(double sacks, double kilosOnDisplay, double kilosPerSack, double sacksIncrement,
			double kilosOnDisplayIncrement) throws NegativeValueException, NotEnoughQuantityException, ZeroKilosPerSackException {
		ProductQuantity pQuantity = new ProductQuantity(sacks, kilosOnDisplay, kilosPerSack);
		if (validIncrement(sacks, kilosOnDisplay, kilosPerSack, sacksIncrement, kilosOnDisplayIncrement)) {
			pQuantity = new ProductQuantity(sacks + sacksIncrement, kilosOnDisplay + kilosOnDisplayIncrement, kilosPerSack);
		}
		return pQuantity;
	}

	public static ProductQuantity computeDecrementResult(double sacks, double kilosOnDisplay, double kilosPerSack, double sacksDecrement,
			double kilosOnDisplayDecrement) throws NegativeValueException, NotEnoughQuantityException, ZeroKilosPerSackException {
		ProductQuantity pQuantity = new ProductQuantity(sacks, kilosOnDisplay, kilosPerSack);
		if (validDecrement(sacks, kilosOnDisplay, kilosPerSack, sacksDecrement, kilosOnDisplayDecrement)) {
			if (kilosOnDisplayDecrement <= kilosOnDisplay) {
				pQuantity = new ProductQuantity(sacks - sacksDecrement, kilosOnDisplay - kilosOnDisplayDecrement, kilosPerSack);
			} else {
				double kilosQuantity = totalKilos(sacks, kilosOnDisplay, kilosPerSack);
				double decrementKilosQuantity = totalKilos(sacksDecrement, kilosOnDisplayDecrement, kilosPerSack);
				kilosQuantity -= decrementKilosQuantity;
				pQuantity = simplify(kilosQuantity, kilosPerSack);
			}
		}
		return pQuantity;
	}

	public static double totalSacks(double sacks, double kilos, double klsPerSk) {
		return sacks + (kilos / klsPerSk);
	}

	public static double totalKilos(double sacks, double kilos, double klsPerSk) {
		return (sacks * klsPerSk) + kilos;
	}

	// public static ProductQuantity simplify(double quantityInSack, double
	// quantityInKilo, double kilosPerSack) throws NegativeValueException,
	// ZeroKilosPerSackException {
	// if (valid(quantityInSack, quantityInKilo, kilosPerSack)) {
	// double kilosQuantity = totalKilos(quantityInSack, quantityInKilo,
	// kilosPerSack);
	// double rawResult = kilosQuantity / kilosPerSack;
	// // rounded two decimal result
	// double roundedTwodecimalResult = Math.round(rawResult * 100.0d) / 100.0d;
	// double sacks = Math.floor(roundedTwodecimalResult);
	// // double kilos = ((roundedTwodecimalResult -
	// // Math.floor(roundedTwodecimalResult)) * 100.0d) / 100.0d;
	// double kilos = kilosQuantity % kilosPerSack;
	// ProductQuantity pq = new ProductQuantity(sacks, kilos, kilosPerSack);
	// return pq;
	// } else {
	// throw new NegativeValueException();
	// }
	// }
	//
	public static ProductQuantity simplify(double totalQuantityInKilo, double kilosPerSack) throws NegativeValueException, ZeroKilosPerSackException {
		if (valid(0d, totalQuantityInKilo, kilosPerSack)) {
			double rawResult = totalQuantityInKilo / kilosPerSack;
			// rounded two decimal result
			double roundedTwodecimalResult = Math.round(rawResult * 100.0d) / 100.0d;
			double sacks = Math.floor(roundedTwodecimalResult);
			// double kilos = ((roundedTwodecimalResult -
			// Math.floor(roundedTwodecimalResult)) * 100.0d) / 100.0d;
			double kilos = totalQuantityInKilo % kilosPerSack;
			ProductQuantity pq = new ProductQuantity(sacks, kilos, kilosPerSack);
			return pq;
		} else {
			throw new NegativeValueException();
		}
	}

}
