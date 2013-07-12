package common.manager;

import java.util.List;

import common.entity.inventorysheet.InventorySheetData;

public interface InventorySheetDataManager {

	public void addInventorySheet(InventorySheetData inventorySheet) throws Exception;

	public InventorySheetData getInventorySheet(int id) throws Exception;

	public List<InventorySheetData> getInventorySheets() throws Exception;

	public void updateInventorySheet(InventorySheetData inventorySheet) throws Exception;

	public void deleteInventorySheet(InventorySheetData inventorySheet) throws Exception;

}
