package common.manager;

import java.util.List;

import common.entity.inventorysheet.InventorySheetData;

public interface InventorySheetDataManager {

	public void addInventorySheetData(InventorySheetData inventorySheetData) throws Exception;

	public InventorySheetData getInventorySheetData(int id) throws Exception;

	public List<InventorySheetData> getInventorySheetsData() throws Exception;

	public InventorySheetData getMostRecentInventorySheetData() throws Exception;

	public void updateInventorySheetData(InventorySheetData inventorySheetData) throws Exception;

	public void deleteInventorySheetData(InventorySheetData inventorySheetData) throws Exception;

	// public double getPreviousActualCashOnHand() throws Exception;

}
