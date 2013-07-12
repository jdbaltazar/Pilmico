package common.entity.inventorysheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;

public class InventorySheet implements InventorySheetManager {

	private InventorySheetData inventorySheetData;
	private Map<Integer, InventorySheetDetail> productInventories = new HashMap<Integer, InventorySheetDetail>();

	public InventorySheet() {
		super();
	}

	public InventorySheet(InventorySheetData inventorySheetData, List<InventorySheetDetail> inventorySheetDetails) {
		super();
		this.inventorySheetData = inventorySheetData;
		init(inventorySheetDetails);
		addDeliveriesToProductInventory(inventorySheetData.getDeliveries());
		addPullOutsToProductInventory(inventorySheetData.getPullouts());
		addSalesToProductInventory(inventorySheetData.getSales());
		addAccountReceivablesToProductInventory(inventorySheetData.getAccountReceivables());
	}

	private void init(List<InventorySheetDetail> productInventories) {
		for (InventorySheetDetail pi : productInventories) {
			this.productInventories.put(pi.getId(), pi);
		}
	}

	public void addDeliveriesToProductInventory(Set<Delivery> deliveries) {
		for (Delivery d : deliveries) {
			Set<DeliveryDetail> deliveryDetails = d.getDeliveryDetails();
			for (DeliveryDetail dd : deliveryDetails) {
				InventorySheetDetail pi = productInventories.get(dd.getProduct().getId());
				pi.setDeliveriesInKilo(pi.getDeliveriesInKilo() + dd.getQuantityInKilo());
				pi.setDeliveriesInSack(pi.getDeliveriesInSack() + dd.getQuantityInSack());
			}
		}
	}

	public void addPullOutsToProductInventory(Set<PullOut> pullOuts) {
		for (PullOut po : pullOuts) {
			Set<PullOutDetail> pullOutDetails = po.getPullOutDetails();
			for (PullOutDetail pod : pullOutDetails) {
				InventorySheetDetail pi = productInventories.get(pod.getProduct().getId());
				pi.setPullOutsInKilo(pi.getPullOutsInKilo() + pod.getQuantityInKilo());
				pi.setPullOutsInSack(pi.getPullOutsInSack() + pod.getQuantityInSack());
			}
		}
	}

	public void addSalesToProductInventory(Set<Sales> sales) {
		for (Sales s : sales) {
			Set<SalesDetail> salesDetails = s.getSalesDetails();
			for (SalesDetail sd : salesDetails) {
				InventorySheetDetail pi = productInventories.get(sd.getProduct().getId());
				pi.setOffTakeInKilo(pi.getOffTakeInKilo() + sd.getQuantityInKilo());
				pi.setOffTakeInSack(pi.getOffTakeInSack() + sd.getQuantityInSack());
			}
		}
	}

	public void addAccountReceivablesToProductInventory(Set<AccountReceivable> accountReceivables) {
		for (AccountReceivable s : accountReceivables) {
			Set<AccountReceivableDetail> arDetails = s.getAccountReceivableDetails();
			for (AccountReceivableDetail ard : arDetails) {
				InventorySheetDetail pi = productInventories.get(ard.getProduct().getId());
				pi.setOffTakeInKilo(pi.getOffTakeInKilo() + ard.getQuantityInKilo());
				pi.setOffTakeInSack(pi.getOffTakeInSack() + ard.getQuantityInSack());
			}
		}
	}

	@Override
	public double getBeginningInventoryInSackForProduct(int productId) {
		return 0;
	}

	@Override
	public double getBeginningInventoryInKiloForProduct(int productId) {
		return 0;
	}

	@Override
	public double getTotalBeginningInventoryInSack() {
		return 0;
	}

	@Override
	public double getTotalBeginningInventoryInKilo() {
		return 0;
	}

	@Override
	public double getOnDisplayInSackForProduct(int productId) {
		return 0;
	}

	@Override
	public double getOnDisplayInKiloForProduct(int productId) {
		return 0;
	}

	@Override
	public double getTotalOnDisplayInSack() {
		return 0;
	}

	@Override
	public double getTotalOnDisplayInKilo() {
		return 0;
	}

	@Override
	public double getDeliveriesInSackForProduct(int productId) {
		return 0;
	}

	@Override
	public double getDeliveriesInKiloForProduct(int productId) {
		return 0;
	}

	@Override
	public double getTotalDeliveriesInSack() {
		return 0;
	}

	@Override
	public double getTotalDeliveriesInKilo() {
		return 0;
	}

	@Override
	public double getOverallCostOfDeliveries() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPulloutsInSackForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPulloutsInKiloForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalPulloutsInSack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalPulloutsInKilo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallCostOfPullouts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEndingInventoryInSackForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEndingInventoryInKiloForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalEndingInventoryInSack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalEndingInventoryInKilo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOfftakesInSackForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOfftakesInKiloForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalOfftakesInSack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalOfftakesInKilo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPriceInSackForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPriceInKiloForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalPricesInSack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalPricesInKilo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCombinedSalesInSackForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCombinedSalesInKiloForProduct(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCombinedSalesInSack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCombinedSalesInKilo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallCombinedSales() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallCashAndCheckSales() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallAccountReceivables() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallAccountReceivablesPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallCashAdvances() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallCashAdvancesPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallPersonalExpenses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallStoreExpenses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallPersonalAndStoreExpenses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallSalaryReleases() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverallDiscounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getActualCashOnHand() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getActualCashCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOverAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShortAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Delivery> getDeliveries() {
		return inventorySheetData.getDeliveries();
	}

	@Override
	public Set<PullOut> getPullOuts() {
		return inventorySheetData.getPullouts();
	}

	@Override
	public Set<Sales> getSales() {
		return inventorySheetData.getSales();
	}

	@Override
	public Set<AccountReceivable> getAccountReceivables() {
		return inventorySheetData.getAccountReceivables();
	}

	@Override
	public Breakdown getBreakdown() {
		return inventorySheetData.getBreakdown();
	}

}
