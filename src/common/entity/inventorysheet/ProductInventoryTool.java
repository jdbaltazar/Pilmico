package common.entity.inventorysheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.pullout.PullOut;
import common.entity.sales.Sales;

public class ProductInventoryTool {

	private Map<Integer, InventorySheetDetail> productInventories = new HashMap<Integer, InventorySheetDetail>();

	public ProductInventoryTool() {
		super();
	}

	public ProductInventoryTool(List<InventorySheetDetail> inventorySheetDetails) {
		super();
		init(inventorySheetDetails);
	}

	private void init(List<InventorySheetDetail> inventorySheetDetails) {
		for (InventorySheetDetail isd : inventorySheetDetails) {
			productInventories.put(isd.getProduct().getId(), isd);
		}
	}

	public void addDeliveriesToProductInventory(Set<Delivery> deliveries) {
		for (Delivery d : deliveries) {
			Set<DeliveryDetail> deliveryDetails = d.getDeliveryDetails();
			for (DeliveryDetail dd : deliveryDetails) {
				
			}
		}
	}

	public void addPullOutsToProductInventory(Set<PullOut> pullOuts) {
	}

	public void addSalesToProductInventory(Set<Sales> sales) {
	}

	public void addAccountReceivablesToProductInventory(Set<AccountReceivable> accountReceivables) {
	}

}
