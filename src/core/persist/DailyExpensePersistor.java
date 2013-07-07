package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;
import common.manager.DailyExpensesManager;

public class DailyExpensePersistor extends Persistor implements DailyExpensesManager {

	@Override
	public void addDailyExpenses(DailyExpenses expense) throws Exception {
		add(expense);

		System.out.println("de added!");
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

}
