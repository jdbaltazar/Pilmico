package common.entity.inventorysheet;

import common.entity.product.Product;

public class InventorySheetDetail {

	private InventorySheetDataDetail inventorySheetDataDetail;
	private double deliveriesInSack;
	private double deliveriesInKilo;
	private double pulloutsInSack;
	private double pulloutsInKilo;
	private double cashAndCheckOffTakeInSack;
	private double cashAndCheckOffTakeInKilo;
	private double accountReceivableOffTakeInSack;
	private double accountReceivableOffTakeInKilo;

	// private double offTakeInSack; // sales + account receivables
	// private double offTakeInKilo; // sales + account receivables

	public InventorySheetDetail(InventorySheetDataDetail inventorySheetDataDetail) {
		super();
		this.inventorySheetDataDetail = inventorySheetDataDetail;
		this.deliveriesInSack = 0;
		this.deliveriesInKilo = 0;
		this.pulloutsInSack = 0;
		this.pulloutsInKilo = 0;
		this.cashAndCheckOffTakeInSack = 0;
		this.cashAndCheckOffTakeInKilo = 0;
		this.accountReceivableOffTakeInSack = 0;
		this.accountReceivableOffTakeInKilo = 0;
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

	public double getCashAndCheckOffTakeInSack() {
		return cashAndCheckOffTakeInSack;
	}

	public double getCashAndCheckOffTakeInSackAmount() {
		return cashAndCheckOffTakeInSack * inventorySheetDataDetail.getPricePerSack();
	}

	public void setCashAndCheckOffTakeInSack(double cashAndCheckOffTakeInSack) {
		this.cashAndCheckOffTakeInSack = cashAndCheckOffTakeInSack;
	}

	public double getCashAndCheckOffTakeInKilo() {
		return cashAndCheckOffTakeInKilo;
	}

	public double getCashAndCheckOffTakeInKiloAmount() {
		return cashAndCheckOffTakeInKilo * inventorySheetDataDetail.getPricePerKilo();
	}

	public void setCashAndCheckOffTakeInKilo(double cashAndCheckOffTakeInKilo) {
		this.cashAndCheckOffTakeInKilo = cashAndCheckOffTakeInKilo;
	}

	public double getAccountReceivableOffTakeInSack() {
		return accountReceivableOffTakeInSack;
	}

	public double getAccountReceivableOffTakeInSackAmount() {
		return accountReceivableOffTakeInSack * inventorySheetDataDetail.getPricePerSack();
	}

	public void setAccountReceivableOffTakeInSack(double accountReceivableOffTakeInSack) {
		this.accountReceivableOffTakeInSack = accountReceivableOffTakeInSack;
	}

	public double getAccountReceivableOffTakeInKilo() {
		return accountReceivableOffTakeInKilo;
	}

	public double getAccountReceivableOffTakeInKiloAmount() {
		return accountReceivableOffTakeInKilo * inventorySheetDataDetail.getPricePerKilo();
	}

	public void setAccountReceivableOffTakeInKilo(double accountReceivableOffTakeInKilo) {
		this.accountReceivableOffTakeInKilo = accountReceivableOffTakeInKilo;
	}

	/*
	 * sales + account receivables
	 */
	public double getOffTakeInSack() {
		return cashAndCheckOffTakeInSack + accountReceivableOffTakeInSack;
	}

	/*
	 * sales amount + account receivables amount
	 */
	public double getOffTakeInSackAmount() {
		return getCashAndCheckOffTakeInSackAmount() + getAccountReceivableOffTakeInKiloAmount();
	}

	/*
	 * sales + account receivables
	 */
	public double getOffTakeInKilo() {
		return cashAndCheckOffTakeInKilo + accountReceivableOffTakeInKilo;
	}

	/*
	 * sales amount + account receivables amount
	 */
	public double getOffTakeInKiloAmount() {
		return getCashAndCheckOffTakeInSackAmount() + getCashAndCheckOffTakeInKiloAmount();
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
		return getOffTakeInSack() * inventorySheetDataDetail.getPricePerSack();
	}

	public double getCombinedSalesAmountForKilo() {
		return getOffTakeInKilo() * inventorySheetDataDetail.getPricePerKilo();
	}

	public void computeValues() {
		double beginningInventoryInSack = (getProduct().getSacks() + getOffTakeInSack() + pulloutsInSack) - deliveriesInSack;
		double beginningInventoryInKilo = (getProduct().getKilosOnDisplay() + getOffTakeInKilo() + pulloutsInKilo) - deliveriesInKilo;
		inventorySheetDataDetail.setBeginningInventoryInSack(beginningInventoryInSack);
		inventorySheetDataDetail.setBeginningInventoryInKilo(beginningInventoryInKilo);

		// System.out.println("beg inv in sack for product " +
		// getProduct().getName() + ": " + beginningInventoryInSack);
	}
}
