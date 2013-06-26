package common.manager;

import java.util.List;

import common.entity.expense.Expense;
import common.entity.expense.ExpenseType;

public interface ExpenseManager {

	// expense type

	public void addExpenseType(ExpenseType expenseType) throws Exception;

	public ExpenseType getExpenseType(int id) throws Exception;

	public List<ExpenseType> getExpenseTypes() throws Exception;

	public void updateExpenseType(ExpenseType expenseType) throws Exception;

	public void deleteExpenseType(ExpenseType expenseType) throws Exception;

	public void deleteExpenseType(int id) throws Exception;

	// expense

	public void addExpense(Expense expense) throws Exception;

	public Expense getExpense(int id) throws Exception;

	public List<Expense> getExpenses() throws Exception;

	public void updateExpense(Expense expense) throws Exception;

	public void deleteExpense(Expense expense) throws Exception;

	public void deleteExpense(int id) throws Exception;

}
