package common.entity.inventorysheet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import common.entity.product.Product;

@Entity
@Table(name = "InventorySheetDetail")
public class InventorySheetDataDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "inventory_sheet_id")
	private InventorySheetData inventorySheetData;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "beg_inventory_in_sack")
	private double beginningInventoryInSack;

	@Column(name = "beg_inventory_in_kilo")
	private double beginningInventoryInKilo;

	@Column(name = "on_display_in_sack")
	private double onDisplayInSack;

	@Column(name = "on_display_in_kilo")
	private double onDisplayInKilo;

	@Column(name = "price_per_sack")
	private double pricePerSack;

	@Column(name = "price_per_kilo")
	private double pricePerKilo;

	public InventorySheetDataDetail() {
		super();
	}

	// public InventorySheetDataDetail(InventorySheetData inventorySheetData,
	// Product product, double beginningInventoryInSack,
	// double beginningInventoryInKilo, double onDisplayInSack, double
	// onDisplayInKilo, double pricePerSack, double pricePerKilo) {
	// super();
	// this.inventorySheetData = inventorySheetData;
	// this.product = product;
	// this.beginningInventoryInSack = beginningInventoryInSack;
	// this.beginningInventoryInKilo = beginningInventoryInKilo;
	// this.onDisplayInSack = onDisplayInSack;
	// this.onDisplayInKilo = onDisplayInKilo;
	// this.pricePerSack = pricePerSack;
	// this.pricePerKilo = pricePerKilo;
	// }

	public InventorySheetDataDetail(InventorySheetData inventorySheetData, Product product, double onDisplayInSack, double onDisplayInKilo,
			double pricePerSack, double pricePerKilo) {
		super();
		this.inventorySheetData = inventorySheetData;
		this.product = product;
		this.beginningInventoryInSack = 0d;
		this.beginningInventoryInKilo = 0d;
		this.onDisplayInSack = onDisplayInSack;
		this.onDisplayInKilo = onDisplayInKilo;
		this.pricePerSack = pricePerSack;
		this.pricePerKilo = pricePerKilo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InventorySheetData getInventorySheetData() {
		return inventorySheetData;
	}

	public void setInventorySheetData(InventorySheetData inventorySheetData) {
		this.inventorySheetData = inventorySheetData;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getBeginningInventoryInSack() {
		return beginningInventoryInSack;
	}

	public void setBeginningInventoryInSack(double beginningInventoryInSack) {
		this.beginningInventoryInSack = beginningInventoryInSack;
	}

	public double getBeginningInventoryInKilo() {
		return beginningInventoryInKilo;
	}

	public void setBeginningInventoryInKilo(double beginningInventoryInKilo) {
		this.beginningInventoryInKilo = beginningInventoryInKilo;
	}

	public double getOnDisplayInSack() {
		return onDisplayInSack;
	}

	public void setOnDisplayInSack(double onDisplayInSack) {
		this.onDisplayInSack = onDisplayInSack;
	}

	public double getOnDisplayInKilo() {
		return onDisplayInKilo;
	}

	public void setOnDisplayInKilo(double onDisplayInKilo) {
		this.onDisplayInKilo = onDisplayInKilo;
	}

	public double getPricePerSack() {
		return pricePerSack;
	}

	public void setPricePerSack(double pricePerSack) {
		this.pricePerSack = pricePerSack;
	}

	public double getPricePerKilo() {
		return pricePerKilo;
	}

	public void setPricePerKilo(double pricePerKilo) {
		this.pricePerKilo = pricePerKilo;
	}

}
