package common.entity.inventorysheet;

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
public class InventorySheetDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "inventory_sheet_id")
	private InventorySheet inventorySheet;

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

	private double deliveriesInSack;

	private double deliveriesInKilo;

	private double pulloutsInSack;

	private double pulloutsInKilo;

	// sales + account receivables
	private double offTakeInSack;

	// sales + account receivables
	private double offTakeInKilo;

	public InventorySheetDetail() {
		super();
	}

	public InventorySheetDetail(InventorySheet inventorySheet, Product product, double beginningInventoryInSack, double beginningInventoryInKilo,
			double onDisplayInSack, double onDisplayInKilo, double pricePerSack, double pricePerKilo) {
		super();
		this.inventorySheet = inventorySheet;
		this.product = product;
		this.beginningInventoryInSack = beginningInventoryInSack;
		this.beginningInventoryInKilo = beginningInventoryInKilo;
		this.onDisplayInSack = onDisplayInSack;
		this.onDisplayInKilo = onDisplayInKilo;
		this.pricePerSack = pricePerSack;
		this.pricePerKilo = pricePerKilo;

		this.deliveriesInSack = 0;
		this.deliveriesInKilo = 0;
		this.pulloutsInSack = 0;
		this.pulloutsInKilo = 0;
		this.offTakeInSack = 0;
		this.offTakeInKilo = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InventorySheet getInventorySheet() {
		return inventorySheet;
	}

	public void setInventorySheet(InventorySheet inventorySheet) {
		this.inventorySheet = inventorySheet;
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

	public double getDeliveriesInSack() {
		return deliveriesInSack;
	}

	public void setDeliveriesInSack(double deliveriesInSack) {
		this.deliveriesInSack = deliveriesInSack;
	}

	public double getDeliveriesInKilo() {
		return deliveriesInKilo;
	}

	public void setDeliveriesInKilo(double deliveriesInKilo) {
		this.deliveriesInKilo = deliveriesInKilo;
	}

	public double getPulloutsInSack() {
		return pulloutsInSack;
	}

	public void setPulloutsInSack(double pulloutsInSack) {
		this.pulloutsInSack = pulloutsInSack;
	}

	public double getPulloutsInKilo() {
		return pulloutsInKilo;
	}

	public void setPulloutsInKilo(double pulloutsInKilo) {
		this.pulloutsInKilo = pulloutsInKilo;
	}

	public double getOffTakeInSack() {
		return offTakeInSack;
	}

	public void setOffTakeInSack(double offTakeInSack) {
		this.offTakeInSack = offTakeInSack;
	}

	public double getOffTakeInKilo() {
		return offTakeInKilo;
	}

	public void setOffTakeInKilo(double offTakeInKilo) {
		this.offTakeInKilo = offTakeInKilo;
	}

}
