package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.delivery.Delivery;
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

	// @Override
	// public List<InventorySheet> getInventorySheets() throws Exception {
	// return getAll(InventorySheet.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<InventorySheet> getInventorySheets() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(InventorySheet.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<InventorySheet> inventorySheets = new ArrayList<InventorySheet>();
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
	public void updateInventorySheet(InventorySheet inventorySheet) throws Exception {
		update(inventorySheet);
	}

	@Override
	public void deleteInventorySheet(InventorySheet inventorySheet) throws Exception {
		remove(inventorySheet);
	}

}
