package common.manager;

import java.util.List;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesType;

public interface ExpenseManager {

	// expense type

	public void addExpenseType(DailyExpensesType expenseType) throws Exception;

	public DailyExpensesType getExpenseType(int id) throws Exception;

	public List<DailyExpensesType> getExpenseTypes() throws Exception;

	public void updateExpenseType(DailyExpensesType expenseType) throws Exception;

	public void deleteExpenseType(DailyExpensesType expenseType) throws Exception;

	public void deleteExpenseType(int id) throws Exception;

	// expense

	public void addExpense(DailyExpenses expense) throws Exception;

	public DailyExpenses getExpense(int id) throws Exception;

	public List<DailyExpenses> getExpenses() throws Exception;

	public void updateExpense(DailyExpenses expense) throws Exception;

	public void deleteExpense(DailyExpenses expense) throws Exception;

	public void deleteExpense(int id) throws Exception;

}
