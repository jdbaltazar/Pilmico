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
	private int maxId = 0;

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
			if (pi.getProduct().getId() > maxId)
				maxId = pi.getProduct().getId();
		}
	}

	private void addDeliveriesToProductInventory(Set<Delivery> deliveries) {
		for (Delivery d : deliveries) {
			Set<DeliveryDetail> deliveryDetails = d.getDeliveryDetails();
			for (DeliveryDetail dd : deliveryDetails) {
				InventorySheetDetail pi = productInventories.get(dd.getProduct().getId());
				pi.setDeliveriesInKilo(pi.getDeliveriesInKilo() + dd.getQuantityInKilo());
				pi.setDeliveriesInSack(pi.getDeliveriesInSack() + dd.getQuantityInSack());
			}
		}
	}

	private void addPullOutsToProductInventory(Set<PullOut> pullOuts) {
		for (PullOut po : pullOuts) {
			Set<PullOutDetail> pullOutDetails = po.getPullOutDetails();
			for (PullOutDetail pod : pullOutDetails) {
				InventorySheetDetail pi = productInventories.get(pod.getProduct().getId());
				pi.setPullOutsInKilo(pi.getPullOutsInKilo() + pod.getQuantityInKilo());
				pi.setPullOutsInSack(pi.getPullOutsInSack() + pod.getQuantityInSack());
			}
		}
	}

	private void addSalesToProductInventory(Set<Sales> sales) {
		for (Sales s : sales) {
			Set<SalesDetail> salesDetails = s.getSalesDetails();
			for (SalesDetail sd : salesDetails) {
				InventorySheetDetail pi = productInventories.get(sd.getProduct().getId());
				pi.setOffTakeInKilo(pi.getOffTakeInKilo() + sd.getQuantityInKilo());
				pi.setOffTakeInSack(pi.getOffTakeInSack() + sd.getQuantityInSack());
			}
		}
	}

	private void addAccountReceivablesToProductInventory(Set<AccountReceivable> accountReceivables) {
		for (AccountReceivable s : accountReceivables) {
			Set<AccountReceivableDetail> arDetails = s.getAccountReceivableDetails();
			for (AccountReceivableDetail ard : arDetails) {
				InventorySheetDetail pi = productInventories.get(ard.getProduct().getId());
				pi.setOffTakeInKilo(pi.getOffTakeInKilo() + ard.getQuantityInKilo());
				pi.setOffTakeInSack(pi.getOffTakeInSack() + ard.getQuantityInSack());
			}
		}
	}

	public int getMaxId() {
		return maxId;
	}

	@Override
	public double getBeginningInventoryInSackForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getBeginningInventoryInSack();
		return 0d;
	}

	@Override
	public double getBeginningInventoryInKiloForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getBeginningInventoryInKilo();
		return 0d;
	}

	@Override
	public double getTotalBeginningInventoryInSack() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getBeginningInventoryInSackForProduct(i);
		}
		return total;
	}

	@Override
	public double getTotalBeginningInventoryInKilo() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getBeginningInventoryInKiloForProduct(i);
		}
		return total;
	}

	@Override
	public double getOnDisplayInSackForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getOnDisplayInSack();
		return 0d;
	}

	@Override
	public double getOnDisplayInKiloForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getOnDisplayInKilo();
		return 0d;
	}

	@Override
	public double getTotalOnDisplayInSack() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getOnDisplayInSackForProduct(i);
		}
		return total;
	}

	@Override
	public double getTotalOnDisplayInKilo() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getOnDisplayInKiloForProduct(i);
		}
		return total;
	}

	@Override
	public double getDeliveriesInSackForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getDeliveriesInSack();
		return 0d;
	}

	@Override
	public double getDeliveriesInKiloForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getDeliveriesInKilo();
		return 0d;
	}

	@Override
	public double getTotalDeliveriesInSack() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getDeliveriesInSackForProduct(i);
		}
		return total;
	}

	@Override
	public double getTotalDeliveriesInKilo() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getDeliveriesInKiloForProduct(i);
		}
		return total;
	}

	@Override
	public double getOverallCostOfDeliveries() {
		return 0;
	}

	@Override
	public double getPulloutsInSackForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getPullOutsInSack();
		return 0d;
	}

	@Override
	public double getPulloutsInKiloForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getPullOutsInKilo();
		return 0d;
	}

	@Override
	public double getTotalPulloutsInSack() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getPulloutsInSackForProduct(i);
		}
		return total;
	}

	@Override
	public double getTotalPulloutsInKilo() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getPulloutsInKiloForProduct(i);
		}
		return total;
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
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getOffTakeInSack();
		return 0d;
	}

	@Override
	public double getOfftakesInKiloForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getOffTakeInKilo();
		return 0d;
	}

	@Override
	public double getTotalOfftakesInSack() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getOfftakesInSackForProduct(i);
		}
		return total;
	}

	@Override
	public double getTotalOfftakesInKilo() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getOfftakesInKiloForProduct(i);
		}
		return total;
	}

	@Override
	public double getPricePerSackForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getPricePerSack();
		return 0d;
	}

	@Override
	public double getPricePerKiloForProduct(int productId) {
		InventorySheetDetail isd = productInventories.get(productId);
		if (isd != null)
			return isd.getPricePerKilo();
		return 0d;
	}

	@Override
	public double getTotalPricesInSack() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getPricePerSackForProduct(i);
		}
		return total;
	}

	@Override
	public double getTotalPricesInKilo() {
		double total = 0d;
		for (int i = 0; i < maxId; i++) {
			total += getPricePerKiloForProduct(i);
		}
		return total;
	}

	@Override
	public double getCombinedSalesInSackForProduct(int productId) {
		return 0;
	}

	@Override
	public double getCombinedSalesInKiloForProduct(int productId) {
		return 0;
	}

	@Override
	public double getCombinedSalesInSack() {
		return 0;
	}

	@Override
	public double getCombinedSalesInKilo() {
		return 0;
	}

	@Override
	public double getOverallCombinedSales() {
		return 0;
	}

	@Override
	public double getOverallCashAndCheckSales() {
		double total = 0d;
		Set<Sales> sales = inventorySheetData.getSales();
		for (Sales s : sales) {
			total += s.getSalesAmount();
		}
		return total;
	}

	@Override
	public double getOverallAccountReceivables() {
		double total = 0d;
		Set<AccountReceivable> ars = inventorySheetData.getAccountReceivables();
		for (AccountReceivable ar : ars) {
			total += ar.getAccountReceivablesAmount();
		}
		return total;
	}

	@Override
	public double getOverallAccountReceivablesPayments() {
		double total = 0d;
		// Set<ARPayment> ars = inventorySheetData.get
		// for (AccountReceivable ar : ars) {
		// total += ar.getAccountReceivablesAmount();
		// }
		return total;
	}

	@Override
	public double getOverallCashAdvances() {
		return 0;
	}

	@Override
	public double getOverallCashAdvancesPayments() {
		return 0;
	}

	@Override
	public double getOverallPersonalExpenses() {
		return 0;
	}

	@Override
	public double getOverallStoreExpenses() {
		return 0;
	}

	@Override
	public double getOverallPersonalAndStoreExpenses() {
		return 0;
	}

	@Override
	public double getOverallSalaryReleases() {
		return 0;
	}

	@Override
	public double getOverallDiscounts() {
		return 0;
	}

	@Override
	public double getActualCashOnHand() {
		return 0;
	}

	@Override
	public double getActualCashCount() {
		return 0;
	}

	@Override
	public double getOverAmount() {
		return 0;
	}

	@Override
	public double getShortAmount() {
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
