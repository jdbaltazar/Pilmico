package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.inventorysheet.Denomination;
import common.entity.inventorysheet.InventorySheetData;

public interface InventorySheetDataManager {

	public void addInventorySheetData(InventorySheetData inventorySheetData) throws Exception;

	public InventorySheetData getInventorySheetData(int id) throws Exception;

	public List<InventorySheetData> getInventorySheetsData() throws Exception;

	public InventorySheetData getMostRecentInventorySheetData() throws Exception;

	public void updateInventorySheetData(InventorySheetData inventorySheetData) throws Exception;

	public void deleteInventorySheetData(InventorySheetData inventorySheetData) throws Exception;

	// public InventorySheetData getInventorySheetDataWithThisDate(Date date)
	// throws Exception;

	public boolean isValidFor(Date date) throws Exception;

	public String getValidityRemarksFor(Date date) throws Exception;

	// public double getPreviousActualCashOnHand() throws Exception;

	// denonimation

	public void addDenominations(Denomination denomination) throws Exception;

	public Denomination getDenomination(int id) throws Exception;

	public Denomination searchDenomination(double value) throws Exception;

	public List<Denomination> getDenominations() throws Exception;

	public void updateDenominations(Denomination denomination) throws Exception;

	public void deleteDenomination(Denomination denomination) throws Exception;

	public void deleteDenomination(int id) throws Exception;

}
