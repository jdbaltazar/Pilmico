package core.persist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.inventorysheet.InventorySheetData;
import common.manager.InventorySheetDataManager;

public class InventorySheetDataPersistor extends Persistor implements InventorySheetDataManager {

	@Override
	public void addInventorySheetData(InventorySheetData inventorySheetData) throws Exception {
		add(inventorySheetData);
	}

	@Override
	public InventorySheetData getInventorySheetData(int id) throws Exception {
		return (InventorySheetData) get(InventorySheetData.class, id);
	}

	// @Override
	// public List<InventorySheet> getInventorySheets() throws Exception {
	// return getAll(InventorySheet.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<InventorySheetData> getInventorySheetsData() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(InventorySheetData.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<InventorySheetData> inventorySheetsData = new ArrayList<InventorySheetData>();
		try {
			inventorySheetsData = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return inventorySheetsData;
	}

	@Override
	public InventorySheetData getMostRecentInventorySheetData() throws Exception {
		List<InventorySheetData> inventorySheetsData = getInventorySheetsData();
		if (inventorySheetsData.size() > 0)
			return inventorySheetsData.get(0);
		return null;
	}

	@Override
	public void updateInventorySheetData(InventorySheetData inventorySheetData) throws Exception {
		update(inventorySheetData);
	}

	@Override
	public void deleteInventorySheetData(InventorySheetData inventorySheetData) throws Exception {
		remove(inventorySheetData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public InventorySheetData getInventorySheetDataWithThisDate(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(InventorySheetData.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<InventorySheetData> inventorySheetsData = new ArrayList<InventorySheetData>();
		InventorySheetData isd = null;
		try {
			inventorySheetsData = criteria.add(Restrictions.like("date", date)).addOrder(Order.desc("date")).list();
			if (inventorySheetsData.size() > 0) {
				isd = inventorySheetsData.get(0);
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return isd;
	}

	// @Override
	// public double getPreviousActualCashOnHand() throws Exception {
	// InventorySheetData inventorySheetData = getMostRecentInventorySheetData();
	// if (inventorySheetData != null) {
	// InventorySheet inventorySheet = new InventorySheet(inventorySheetData);
	// return inventorySheet.getCashOnHandt();
	// } else {
	// throw new NullPointerException();
	// }
	// }

}
