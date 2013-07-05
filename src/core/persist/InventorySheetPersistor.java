package core.persist;

import java.util.List;

import common.entity.inventorysheet.InventorySheet;
import common.manager.InventorySheetManager;

public class InventorySheetPersistor extends Persistor implements InventorySheetManager {

	@Override
	public void addInventorySheet(InventorySheet inventorySheet) throws Exception {
		add(inventorySheet);
	}

	@Override
	public InventorySheet getInventorySheet(int id) throws Exception {
		return (InventorySheet) get(InventorySheet.class, id);
	}

	@Override
	public List<InventorySheet> getInventorySheets() throws Exception {
		return getAll(InventorySheet.class);
	}

	@Override
	public void updateInventorySheet(InventorySheet inventorySheet) throws Exception {
		update(inventorySheet);
	}

	@Override
	public void deleteInventorySheet(InventorySheet inventorySheet) throws Exception {
		remove(inventorySheet);
	}

}
