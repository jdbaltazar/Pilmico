package common.manager;

import java.util.List;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesType;

public interface DailyExpensesManager {

	// expense type

	public void addExpenseType(DailyExpensesType expenseType) throws Exception;

	public DailyExpensesType getExpenseType(int id) throws Exception;

	public List<DailyExpensesType> getExpenseTypes() throws Exception;

	public void updateExpenseType(DailyExpensesType expenseType) throws Exception;

	public void deleteExpenseType(DailyExpensesType expenseType) throws Exception;

	public void deleteExpenseType(int id) throws Exception;

	// daily expenses

	public void addDailyExpenses(DailyExpenses dailyExpenses) throws Exception;

	public DailyExpenses getDailyExpense(int id) throws Exception;

	public List<DailyExpenses> getDailyExpenses() throws Exception;

	public void updateDailyExpenses(DailyExpenses dailyExpenses) throws Exception;

	public void deleteDailyExpenses(DailyExpenses dailyExpenses) throws Exception;

	public void deleteDailyExpenses(int id) throws Exception;

}
