package common.entity.inventorysheet;

import common.entity.product.Product;

public class InventorySheetDetail {

	private InventorySheetDataDetail inventorySheetDataDetail;
	private double deliveriesInSack;
	private double deliveriesInKilo;
	private double pulloutsInSack;
	private double pulloutsInKilo;
	private double offTakeInSack; // sales + account receivables
	private double offTakeInKilo; // sales + account receivables

	public InventorySheetDetail(InventorySheetDataDetail inventorySheetDataDetail) {
		super();
		this.inventorySheetDataDetail = inventorySheetDataDetail;
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

	public InventorySheetDataDetail getInventoryDataSheetDetail() {
		return inventorySheetDataDetail;
	}

	public int getId() {
		return inventorySheetDataDetail.getId();
	}

	public InventorySheetData getInventorySheet() {
		return inventorySheetDataDetail.getInventorySheetData();
	}

	public Product getProduct() {
		return inventorySheetDataDetail.getProduct();
	}

	public double getBeginningInventoryInSack() {
		return inventorySheetDataDetail.getBeginningInventoryInSack();
	}

	public double getBeginningInventoryInKilo() {
		return inventorySheetDataDetail.getBeginningInventoryInKilo();
	}

	public double getOnDisplayInSack() {
		return inventorySheetDataDetail.getOnDisplayInSack();
	}

	public double getOnDisplayInKilo() {
		return inventorySheetDataDetail.getOnDisplayInKilo();
	}

	public double getPricePerSack() {
		return inventorySheetDataDetail.getPricePerSack();
	}

	public double getPricePerKilo() {
		return inventorySheetDataDetail.getPricePerKilo();
	}

	/*
	 * beginning inventory - on display + deliveries - pullouts
	 */
	public double getEndingInventoryInSack() {
		return ((getBeginningInventoryInSack() - getOnDisplayInSack()) + getDeliveriesInSack()) - getPullOutsInSack();
	}

	/*
	 * beginning inventory - on display + deliveries - pullouts
	 */
	public double getEndingInventoryInKilo() {
		return ((getBeginningInventoryInKilo() - getOnDisplayInKilo()) + getDeliveriesInKilo()) - getPullOutsInKilo();
	}

	public double getCombinedSalesAmountForSack() {
		return offTakeInSack * inventorySheetDataDetail.getPricePerSack();
	}

	public double getCombinedSalesAmountForKilo() {
		return offTakeInKilo * inventorySheetDataDetail.getPricePerKilo();
	}
}
