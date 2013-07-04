package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.manager.CashAdvanceManager;

public class CashAdvancePersistor extends Persistor implements CashAdvanceManager {

	@Override
	public void addCashAdvance(CashAdvance cashAdvance) throws Exception {
		add(cashAdvance);
	}

	@Override
	public CashAdvance getCashAdvance(int id) throws Exception {
		return (CashAdvance) get(CashAdvance.class, id);
	}

	// @Override
	// public List<CashAdvance> getCashAdvances() throws Exception {
	// return getAll(CashAdvance.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getCashAdvances() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			cashAdvances = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@Override
	public void updateCashAdvance(CashAdvance cashAdvance) throws Exception {
		update(cashAdvance);
	}

	@Override
	public void deleteCashAdvance(CashAdvance cashAdvance) throws Exception {
		remove(cashAdvance);
	}

	@Override
	public void addCAPayment(CAPayment caPayment) throws Exception {
		add(caPayment);
	}

	@Override
	public CAPayment getCAPayment(int id) throws Exception {
		return (CAPayment) get(CAPayment.class, id);
	}

	// @Override
	// public List<CAPayment> getCAPayments() throws Exception {
	// return getAll(CAPayment.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getCAPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		List<CAPayment> caPayments = new ArrayList<CAPayment>();
		try {
			caPayments = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return caPayments;
	}

	@Override
	public void updateCAPayment(CAPayment caPayment) throws Exception {
		update(caPayment);
	}

	@Override
	public void deleteCAPayment(CAPayment caPayment) throws Exception {
		remove(caPayment);
	}

}
