package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.deposit.Deposit;
import common.manager.DepositManager;

public class DepositPersistor extends Persistor implements DepositManager {

	@Override
	public void addDeposit(Deposit deposit) throws Exception {
		add(deposit);
	}

	@Override
	public Deposit getDeposit(int id) throws Exception {
		return (Deposit) get(Deposit.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deposit> getAllDeposits() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Deposit.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Deposit> deposits = new ArrayList<Deposit>();
		try {
			deposits = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deposit> getValidDeposits() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Deposit.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Deposit> deposits = new ArrayList<Deposit>();
		try {
			deposits = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deposit> getInvalidDeposits() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Deposit.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Deposit> deposits = new ArrayList<Deposit>();
		try {
			deposits = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@Override
	public void updateDeposit(Deposit deposit) throws Exception {
		update(deposit);
	}

	@Override
	public void deleteDeposit(Deposit deposit) throws Exception {
		remove(deposit);
	}

}
