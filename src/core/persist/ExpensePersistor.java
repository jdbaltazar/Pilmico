package core.persist;

import java.util.List;

import common.entity.expense.Expense;
import common.entity.expense.ExpenseType;
import common.manager.ExpenseManager;

public class ExpensePersistor extends Persistor implements ExpenseManager {

	@Override
	public void addExpense(Expense expense) throws Exception {
		add(expense);
	}

	@Override
	public Expense getExpense(int id) throws Exception {
		return (Expense) get(Expense.class, id);
	}

	@Override
	public List<Expense> getExpenses() throws Exception {
		return getAll(Expense.class);
	}

	@Override
	public void updateExpense(Expense expense) throws Exception {
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

	@Override
	public void addExpenseType(ExpenseType expenseType) throws Exception {
		add(expenseType);
	}

	@Override
	public ExpenseType getExpenseType(int id) throws Exception {
		return (ExpenseType) get(ExpenseType.class, id);
	}

	@Override
	public List<ExpenseType> getExpenseTypes() throws Exception {
		return getAll(ExpenseType.class);
	}

	@Override
	public void updateExpenseType(ExpenseType expenseType) throws Exception {
		update(expenseType);
	}

	@Override
	public void deleteExpenseType(ExpenseType expenseType) throws Exception {
		remove(expenseType);
	}

	@Override
	public void deleteExpenseType(int id) throws Exception {
		remove(getExpenseType(id));
	}

}
