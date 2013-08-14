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

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;
import common.manager.DailyExpensesManager;

public class DailyExpensePersistor extends Persistor implements DailyExpensesManager {

	@Override
	public void addDailyExpenses(DailyExpenses expense) throws Exception {
		add(expense);
	}

	@Override
	public DailyExpenses getDailyExpense(int id) throws Exception {
		return (DailyExpenses) get(DailyExpenses.class, id);
	}

	// @Override
	// public List<DailyExpenses> getDailyExpenses() throws Exception {
	// return getAll(DailyExpenses.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getAllDailyExpenses() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> expense = new ArrayList<DailyExpenses>();
		try {
			expense = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return expense;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getValidDailyExpenses() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> expense = new ArrayList<DailyExpenses>();
		try {
			expense = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return expense;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getInvalidDailyExpenses() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> expense = new ArrayList<DailyExpenses>();
		try {
			expense = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return expense;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getPendingDailyExpenses() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> expense = new ArrayList<DailyExpenses>();
		try {
			expense = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return expense;
	}

	@Override
	public void updateDailyExpenses(DailyExpenses expense) throws Exception {
		update(expense);
	}

	@Override
	public void deleteDailyExpenses(DailyExpenses expense) throws Exception {
		remove(expense);
	}

	@Override
	public void deleteDailyExpenses(int id) throws Exception {
		remove(getDailyExpense(id));
	}

	@Override
	public void addExpenseType(DailyExpensesType expenseType) throws Exception {
		add(expenseType);
	}

	@Override
	public DailyExpensesType getExpenseType(int id) throws Exception {
		return (DailyExpensesType) get(DailyExpensesType.class, id);
	}

	@Override
	public List<DailyExpensesType> getExpenseTypes() throws Exception {
		return getAll(DailyExpensesType.class);
	}

	@Override
	public void updateExpenseType(DailyExpensesType expenseType) throws Exception {
		update(expenseType);
	}

	@Override
	public void deleteExpenseType(DailyExpensesType expenseType) throws Exception {
		remove(expenseType);
	}

	@Override
	public void deleteExpenseType(int id) throws Exception {
		remove(getExpenseType(id));
	}

	@Override
	public void addExpenses(Expense expense) throws Exception {
		add(expense);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Expense searchExpense(String name) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Expense.class);
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = null;
		try {
			expenses = criteria.add(Restrictions.eq("name", name)).list();
			if (expenses.size() > 0) {
				expense = expenses.get(0);
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return expense;
	}

	@Override
	public Expense getExpense(int id) throws Exception {
		return (Expense) getExpense(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expense> getExpenses() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Expense.class);
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			expenses = criteria.addOrder(Order.asc("name")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return expenses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DailyExpensesType getExpenseType(String name) throws Exception {
		DailyExpensesType det = null;
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpensesType.class);
		List<DailyExpensesType> dets = new ArrayList<DailyExpensesType>();
		try {
			dets = criteria.add(Restrictions.like("name", name)).list();
			if (dets.size() > 0)
				det = dets.get(0);
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return det;
	}

	@Override
	public void updateExpenses(Expense expense) throws Exception {
		update(expense);
	}

	@Override
	public void deleteExpense(Expense expense) throws Exception {
		remove(expense);
	}

	@Override
	public void deleteExpense(int id) throws Exception {
		remove(getExpense(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getMostRecentAmountForExpense(int expenseId) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpensesDetail.class);
		List<DailyExpensesDetail> deDetails = new ArrayList<DailyExpensesDetail>();
		try {
			deDetails = criteria.add(Restrictions.eq("expense.id", expenseId)).addOrder(Order.desc("id")).list();
			if (deDetails.size() > 0) {
				return deDetails.get(0).getAmount();
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return 0d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getAllDailyExpensesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> dailyExpenses = new ArrayList<DailyExpenses>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			dailyExpenses = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date"))
					.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return dailyExpenses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getValidDailyExpensesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> dailyExpenses = new ArrayList<DailyExpenses>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			dailyExpenses = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return dailyExpenses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getInvalidDailyExpensesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> dailyExpenses = new ArrayList<DailyExpenses>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			dailyExpenses = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return dailyExpenses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getPendingDailyExpensesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> dailyExpenses = new ArrayList<DailyExpenses>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			dailyExpenses = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return dailyExpenses;
	}

	@Override
	public List<Date> getDatesOfPendingDailyExpenses() throws Exception {
		List<DailyExpenses> dailyExpenses = getPendingDailyExpenses();
		List<Date> dates = new ArrayList<Date>();
		for (DailyExpenses dailyExpense : dailyExpenses) {
			Date date = DateTool.getDateWithoutTime(dailyExpense.getDate());
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
	public List<DailyExpenses> getPendingDailyExpensesBetween(Date startDate, Date endDate) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> dailyExpensess = new ArrayList<DailyExpenses>();
		try {
			Date lowerBound = DateTool.getTomorrowDate(DateTool.getDateWithoutTime(startDate));
			Date upperBound = DateTool.getDateWithoutTime(endDate);
			dailyExpensess = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return dailyExpensess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyExpenses> getPendingDailyExpensesBefore(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DailyExpenses.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DailyExpenses> dailyExpensess = new ArrayList<DailyExpenses>();
		try {
			dailyExpensess = criteria.add(Restrictions.lt("date", DateTool.getDateWithoutTime(date))).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return dailyExpensess;
	}

}
