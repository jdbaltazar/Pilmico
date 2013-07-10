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

public class ProductInventoryTool {

	private Map<Integer, ProductInventory> productInventories = new HashMap<Integer, ProductInventory>();

	public ProductInventoryTool() {
		super();
	}

	public ProductInventoryTool(List<ProductInventory> productInventories) {
		super();
		init(productInventories);
	}

	private void init(List<ProductInventory> productInventories) {
		for (ProductInventory pi : productInventories) {
			this.productInventories.put(pi.getId(), pi);
		}
	}

	public void addDeliveriesToProductInventory(Set<Delivery> deliveries) {
		for (Delivery d : deliveries) {
			Set<DeliveryDetail> deliveryDetails = d.getDeliveryDetails();
			for (DeliveryDetail dd : deliveryDetails) {
				ProductInventory pi = productInventories.get(dd.getProduct().getId());
				pi.setDeliveriesInKilo(pi.getDeliveriesInKilo() + dd.getQuantityInKilo());
				pi.setDeliveriesInSack(pi.getDeliveriesInSack() + dd.getQuantityInSack());
			}
		}
	}

	public void addPullOutsToProductInventory(Set<PullOut> pullOuts) {
		for (PullOut po : pullOuts) {
			Set<PullOutDetail> pullOutDetails = po.getPullOutDetails();
			for (PullOutDetail pod : pullOutDetails) {
				ProductInventory pi = productInventories.get(pod.getProduct().getId());
				pi.setPullOutsInKilo(pi.getPullOutsInKilo() + pod.getQuantityInKilo());
				pi.setPullOutsInSack(pi.getPullOutsInSack() + pod.getQuantityInSack());
			}
		}
	}

	public void addSalesToProductInventory(Set<Sales> sales) {
		for (Sales s : sales) {
			Set<SalesDetail> salesDetails = s.getSalesDetails();
			for (SalesDetail sd : salesDetails) {
				ProductInventory pi = productInventories.get(sd.getProduct().getId());
				pi.setOffTakeInKilo(pi.getOffTakeInKilo() + sd.getQuantityInKilo());
				pi.setOffTakeInSack(pi.getOffTakeInSack() + sd.getQuantityInSack());
			}
		}
	}

	public void addAccountReceivablesToProductInventory(Set<AccountReceivable> accountReceivables) {
		for (AccountReceivable s : accountReceivables) {
			Set<AccountReceivableDetail> arDetails = s.getAccountReceivableDetails();
			for (AccountReceivableDetail ard : arDetails) {
				ProductInventory pi = productInventories.get(ard.getProduct().getId());
				pi.setOffTakeInKilo(pi.getOffTakeInKilo() + ard.getQuantityInKilo());
				pi.setOffTakeInSack(pi.getOffTakeInSack() + ard.getQuantityInSack());
			}
		}
	}
}
