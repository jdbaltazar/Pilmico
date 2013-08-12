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
	public List<AccountReceivable> getAllAccountReceivables() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getValidAccountReceivables() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			accountReceivables = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getInvalidAccountReceivables() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			accountReceivables = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getPendingAccountReceivables() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {

			accountReceivables = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getAllAccountReceivablesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			accountReceivables = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date"))
					.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getValidAccountReceivablesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			accountReceivables = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getInvalidAccountReceivablesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			accountReceivables = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountReceivable> getPendingAccountReceivablesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			accountReceivables = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
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
	public List<ARPayment> getAllARPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getValidARPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			arPayments = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getInvalidARPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			arPayments = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getPendingARPayments() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			arPayments = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getAllARPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			arPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getValidARPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			arPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getInvalidARPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			arPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getPendingARPaymentsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			arPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
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

	@Override
	public List<Date> getDatesOfPendingAccountReceivables() throws Exception {
		List<AccountReceivable> accountReceivables = getPendingAccountReceivables();
		List<Date> dates = new ArrayList<Date>();
		for (AccountReceivable accountReceivable : accountReceivables) {
			Date date = DateTool.getDateWithoutTime(accountReceivable.getDate());
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

	@Override
	public List<Date> getDatesOfPendingARPayments() throws Exception {
		List<ARPayment> arPayments = getPendingARPayments();
		List<Date> dates = new ArrayList<Date>();
		for (ARPayment arPayment : arPayments) {
			Date date = DateTool.getDateWithoutTime(arPayment.getDate());
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
	public List<AccountReceivable> getPendingAccountReceivablesBetween(Date startDate, Date endDate) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(AccountReceivable.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
		try {
			Date lowerBound = DateTool.getTomorrowDate(DateTool.getDateWithoutTime(startDate));
			Date upperBound = DateTool.getDateWithoutTime(endDate);
			accountReceivables = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accountReceivables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPayment> getPendingARPaymentsBetween(Date startDate, Date endDate) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(ARPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ARPayment> arPayments = new ArrayList<ARPayment>();
		try {
			Date lowerBound = DateTool.getTomorrowDate(DateTool.getDateWithoutTime(startDate));
			Date upperBound = DateTool.getDateWithoutTime(endDate);
			arPayments = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arPayments;
	}

}
