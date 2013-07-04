package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.manager.AccountReceivableManager;

public class AccountReceivablePersistor extends Persistor implements AccountReceivableManager {

	// ar

	@Override
	public void addAccountReceivable(AccountReceivable accountReceivable) throws Exception {
		add(accountReceivable);
	}

	@Override
	public AccountReceivable getAccountReceivable(int id) throws Exception {
		return (AccountReceivable) get(AccountReceivable.class, id);
	}

	// @Override
	// public List<AccountReceivable> getAccountReceivables() throws Exception {
	// return getAll(AccountReceivable.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getAccountReceivables() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			accountReceivables = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@Override
	public void updateAccountReceivable(AccountReceivable accountReceivable) throws Exception {
		update(accountReceivable);
	}

	@Override
	public void deleteAccountReceivable(AccountReceivable accountReceivable) throws Exception {
		remove(accountReceivable);
	}

	@Override
	public void deleteAccountReceivable(int accountReceivableId) throws Exception {
		remove(getAccountReceivable(accountReceivableId));
	}

	// ar payments

	@Override
	public void addARPayment(ARPayment arPayment) throws Exception {
		add(arPayment);
	}

	@Override
	public ARPayment getARPayment(int id) throws Exception {
		return (ARPayment) get(ARPayment.class, id);
	}

	// @Override
	// public List<ARPayment> getARPayments() throws Exception {
	// return getAll(ARPayment.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getARPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			arPayments = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@Override
	public void updateARPayment(ARPayment arPayment) throws Exception {
		update(arPayment);
	}

	@Override
	public void deleteARPayment(ARPayment arPayment) throws Exception {
		remove(arPayment);
	}

	@Override
	public void deleteARPayment(int arPaymentId) throws Exception {
		remove(getARPayment(arPaymentId));
	}

}
