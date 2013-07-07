package core.persist;

import java.util.ArrayList;
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

	@Override
	public void updateDelivery(Delivery delivery) throws Exception {
		update(delivery);
	}

	@Override
	public void deleteDelivery(Delivery delivery) throws Exception {
		remove(delivery);
	}

}
