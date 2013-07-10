package common.entity.inventorysheet;

import common.entity.product.Product;

public class ProductInventory {

	private InventorySheetDetail inventorySheetDetail;

	private double deliveriesInSack;

	private double deliveriesInKilo;

	private double pulloutsInSack;

	private double pulloutsInKilo;

	// sales + account receivables
	private double offTakeInSack;

	// sales + account receivables
	private double offTakeInKilo;

	public ProductInventory(InventorySheetDetail inventorySheetDetail) {
		super();
		this.inventorySheetDetail = inventorySheetDetail;
		this.deliveriesInSack = 0;
		this.deliveriesInKilo = 0;
		this.pulloutsInSack = 0;
		this.pulloutsInKilo = 0;
		this.offTakeInSack = 0;
		this.offTakeInKilo = 0;
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

	public double getPullOutsInSack() {
		return pulloutsInSack;
	}

	public void setPullOutsInSack(double pulloutsInSack) {
		this.pulloutsInSack = pulloutsInSack;
	}

	public double getPullOutsInKilo() {
		return pulloutsInKilo;
	}

	public void setPullOutsInKilo(double pulloutsInKilo) {
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

	public InventorySheetDetail getInventorySheetDetail() {
		return inventorySheetDetail;
	}

	public int getId() {
		return inventorySheetDetail.getId();
	}

	public InventorySheet getInventorySheet() {
		return inventorySheetDetail.getInventorySheet();
	}

	public Product getProduct() {
		return inventorySheetDetail.getProduct();
	}

	public double getBeginningInventoryInSack() {
		return inventorySheetDetail.getBeginningInventoryInSack();
	}

	public double getBeginningInventoryInKilo() {
		return inventorySheetDetail.getBeginningInventoryInKilo();
	}

	public double getOnDisplayInSack() {
		return inventorySheetDetail.getOnDisplayInSack();
	}

	public double getOnDisplayInKilo() {
		return inventorySheetDetail.getOnDisplayInKilo();
	}

	public double getPricePerSack() {
		return inventorySheetDetail.getPricePerSack();
	}

	public double getPricePerKilo() {
		return inventorySheetDetail.getPricePerKilo();
	}
}
