package core.persist;

import gui.forms.util.DateWithoutTime;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.dailyexpenses.Expense;
import common.entity.inventorysheet.Denomination;
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
			inventorySheetsData = criteria.addOrder(Order.desc("date")).addOrder(Order.desc("id")).list();
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

		throw new Exception("Deprecated by JD. Do not use!");
		// Session session = HibernateUtil.startSession();
		// Criteria criteria = session.createCriteria(InventorySheetData.class);
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// List<InventorySheetData> inventorySheetsData = new
		// ArrayList<InventorySheetData>();
		// InventorySheetData isd = null;
		// try {
		// inventorySheetsData = criteria.add(Restrictions.like("date",
		// date)).addOrder(Order.desc("date")).list();
		// if (inventorySheetsData.size() > 0) {
		// isd = inventorySheetsData.get(0);
		// }
		// } catch (HibernateException ex) {
		// ex.printStackTrace();
		// } finally {
		// session.close();
		// }
		// return isd;
	}

	/*
	 * Check if the date is not a future date and date is not equal/earlier than
	 * the most recent inventory sheet
	 */
	@Override
	public boolean isValid(Date date) throws Exception {
		InventorySheetData isd = getMostRecentInventorySheetData();
		if (isd == null)
			return true;
		Date lowerBound = DateWithoutTime.getDateWithoutTime(isd.getDate());
		Date upperBound = DateWithoutTime.getDateWithoutTime(new Date());
		if (date.before(lowerBound) || date.compareTo(lowerBound) == 0 || date.after(upperBound))
			return false;
		return true;
	}

	@Override
	public void addDenominations(Denomination denomination) throws Exception {
		add(denomination);
	}

	@Override
	public Denomination getDenomination(int id) throws Exception {
		return (Denomination) get(Denomination.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Denomination searchDenomination(double value) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Denomination.class);
		List<Denomination> denominations = new ArrayList<Denomination>();
		Denomination d = null;
		try {
			denominations = criteria.add(Restrictions.eq("denomination", value)).addOrder(Order.desc("denomination")).list();
			if (denominations.size() > 0)
				d = denominations.get(0);
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Denomination> getDenominations() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Denomination.class);
		List<Denomination> denominations = new ArrayList<Denomination>();
		try {
			denominations = criteria.addOrder(Order.desc("denomination")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return denominations;
	}

	@Override
	public void updateDenominations(Denomination denomination) throws Exception {
		update(denomination);
	}

	@Override
	public void deleteDenomination(Denomination denomination) throws Exception {
		remove(denomination);
	}

	@Override
	public void deleteDenomination(int id) throws Exception {
		remove(getDenomination(id));
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
