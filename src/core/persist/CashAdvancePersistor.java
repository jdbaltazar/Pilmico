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

import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
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
	public List<CashAdvance> getAllCashAdvances() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getValidCashAdvances() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			cashAdvances = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getInvalidCashAdvances() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			cashAdvances = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getPendingCashAdvances() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			cashAdvances = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date"))
					.list();

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
	public List<CAPayment> getAllCAPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CAPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getValidCAPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> caPayments = new ArrayList<CAPayment>();
		try {
			caPayments = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return caPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getInvalidCAPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Delivery.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> caPayments = new ArrayList<CAPayment>();
		try {
			caPayments = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return caPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getPendingCAPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CAPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> caPayments = new ArrayList<CAPayment>();
		try {
			caPayments = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();

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

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getAllCashAdvancesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cashAdvances = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date"))
					.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getValidCashAdvancesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cashAdvances = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getInvalidCashAdvancesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cashAdvances = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashAdvance> getPendingCashAdvancesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CashAdvance.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CashAdvance> cashAdvances = new ArrayList<CashAdvance>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cashAdvances = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cashAdvances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getAllCAPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CAPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> cAPayments = new ArrayList<CAPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cAPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cAPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getValidCAPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CAPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> cAPayments = new ArrayList<CAPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cAPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cAPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getInvalidCAPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CAPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> cAPayments = new ArrayList<CAPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cAPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cAPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CAPayment> getPendingCAPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(CAPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CAPayment> cAPayments = new ArrayList<CAPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			cAPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cAPayments;
	}

}
