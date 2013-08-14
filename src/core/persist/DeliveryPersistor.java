package core.persist;

import gui.forms.util.DateTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.delivery.Delivery;
import common.manager.DeliveryManager;

public class DeliveryPersistor extends Persistor implements DeliveryManager {

	@Override
	public void addDelivery(Delivery delivery) throws Exception {
		add(delivery);
	}

	@Override
	public Delivery getDelivery(int id) throws Exception {
		return (Delivery) get(Delivery.class, id);
	}

	// @Override
	// public List<Delivery> getDeliveries() throws Exception {
	// return getAll(Delivery.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getAllDeliveries() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			deliveries = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getValidDeliveries() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			deliveries = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getInvalidDeliveries() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			deliveries = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getPendingDeliveries() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			deliveries = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getAccountedDeliveries() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			deliveries = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNotNull("inventorySheetData")).addOrder(Order.desc("date"))
					.list();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@Override
	public void updateDelivery(Delivery delivery) throws Exception {
		update(delivery);
	}

	@Override
	public void deleteDelivery(Delivery delivery) throws Exception {
		remove(delivery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getAllDeliveriesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			deliveries = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getValidDeliveriesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			deliveries = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getInvalidDeliveriesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			deliveries = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getPendingDeliveriesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			deliveries = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@Override
	public List<Date> getDatesOfPendingDeliveries() throws Exception {
		List<Delivery> deliveries = getPendingDeliveries();
		List<Date> dates = new ArrayList<Date>();
		for (Delivery delivery : deliveries) {
			Date date = DateTool.getDateWithoutTime(delivery.getDate());
			boolean found = false;
			for (Date d : dates) {
				if (d.compareTo(date) == 0) {
					found = true;
				}
			}
			if (!found) {
				dates.add(date);
			}
		}
		return dates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getPendingDeliveriesBetween(Date startDate, Date endDate) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			Date lowerBound = DateTool.getTomorrowDate(DateTool.getDateWithoutTime(startDate));
			Date upperBound = DateTool.getDateWithoutTime(endDate);
			deliveries = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> getPendingDeliveriesBefore(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Delivery> deliveries = new ArrayList<Delivery>();
		try {
			deliveries = criteria.add(Restrictions.lt("date", DateTool.getDateWithoutTime(date))).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deliveries;
	}
}
