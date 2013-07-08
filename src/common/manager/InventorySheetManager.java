package common.manager;

import java.util.List;

import common.entity.inventorysheet.InventorySheet;

public interface InventorySheetManager {

	public void addInventorySheet(InventorySheet inventorySheet) throws Exception;

	public InventorySheet getInventorySheet(int id) throws Exception;

	public List<InventorySheet> getInventorySheets() throws Exception;

	public void updateInventorySheet(InventorySheet inventorySheet) throws Exception;

	public void deleteInventorySheet(InventorySheet inventorySheet) throws Exception;

}
