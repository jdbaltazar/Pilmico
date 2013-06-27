package core.persist;

import java.util.List;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesType;
import common.manager.ExpenseManager;

public class ExpensePersistor extends Persistor implements ExpenseManager {

	@Override
	public void addExpense(DailyExpenses expense) throws Exception {
		add(expense);
	}

	@Override
	public DailyExpenses getExpense(int id) throws Exception {
		return (DailyExpenses) get(DailyExpenses.class, id);
	}

	@Override
	public List<DailyExpenses> getExpenses() throws Exception {
		return getAll(DailyExpenses.class);
	}

	@Override
	public void updateExpense(DailyExpenses expense) throws Exception {
		update(expense);
	}

	@Override
	public void deleteExpense(DailyExpenses expense) throws Exception {
		remove(expense);
	}

	@Override
	public void deleteExpense(int id) throws Exception {
		remove(getExpense(id));
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

}
