package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.inventorysheet.InventorySheetData;
import common.manager.InventorySheetDataManager;

public class InventorySheetPersistor extends Persistor implements InventorySheetDataManager {

	@Override
	public void addInventorySheet(InventorySheetData inventorySheet) throws Exception {
		add(inventorySheet);
	}

	@Override
	public InventorySheetData getInventorySheet(int id) throws Exception {
		return (InventorySheetData) get(InventorySheetData.class, id);
	}

	// @Override
	// public List<InventorySheet> getInventorySheets() throws Exception {
	// return getAll(InventorySheet.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<InventorySheetData> getInventorySheets() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(InventorySheetData.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<InventorySheetData> inventorySheets = new ArrayList<InventorySheetData>();
		try {
			inventorySheets = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return inventorySheets;
	}

	@Override
	public void updateInventorySheet(InventorySheetData inventorySheet) throws Exception {
		update(inventorySheet);
	}

	@Override
	public void deleteInventorySheet(InventorySheetData inventorySheet) throws Exception {
		remove(inventorySheet);
	}

}
