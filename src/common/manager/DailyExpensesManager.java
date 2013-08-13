package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;

public interface DailyExpensesManager {

	// expense type

	public void addExpenseType(DailyExpensesType expenseType) throws Exception;

	public DailyExpensesType getExpenseType(int id) throws Exception;

	public DailyExpensesType getExpenseType(String name) throws Exception;

	public List<DailyExpensesType> getExpenseTypes() throws Exception;

	public void updateExpenseType(DailyExpensesType expenseType) throws Exception;

	public void deleteExpenseType(DailyExpensesType expenseType) throws Exception;

	public void deleteExpenseType(int id) throws Exception;

	// daily expenses

	public void addDailyExpenses(DailyExpenses dailyExpenses) throws Exception;

	public DailyExpenses getDailyExpense(int id) throws Exception;

	public List<DailyExpenses> getAllDailyExpenses() throws Exception;

	public List<DailyExpenses> getValidDailyExpenses() throws Exception;

	public List<DailyExpenses> getInvalidDailyExpenses() throws Exception;

	public List<DailyExpenses> getPendingDailyExpenses() throws Exception;

	// excludes the start and end dates
	public List<DailyExpenses> getPendingDailyExpensesBetween(Date startDate, Date endDate) throws Exception;

	public List<DailyExpenses> getPendingDailyExpensesBefore(Date date) throws Exception;

	public void updateDailyExpenses(DailyExpenses dailyExpenses) throws Exception;

	public void deleteDailyExpenses(DailyExpenses dailyExpenses) throws Exception;

	public void deleteDailyExpenses(int id) throws Exception;

	public List<DailyExpenses> getAllDailyExpensesOn(Date date) throws Exception;

	public List<DailyExpenses> getValidDailyExpensesOn(Date date) throws Exception;

	public List<DailyExpenses> getInvalidDailyExpensesOn(Date date) throws Exception;

	public List<DailyExpenses> getPendingDailyExpensesOn(Date date) throws Exception;

	public List<Date> getDatesOfPendingDailyExpenses() throws Exception;

	// expense

	public void addExpenses(Expense expense) throws Exception;

	public Expense getExpense(int id) throws Exception;

	public Expense searchExpense(String name) throws Exception;

	public List<Expense> getExpenses() throws Exception;

	public void updateExpenses(Expense expense) throws Exception;

	public void deleteExpense(Expense expense) throws Exception;

	public void deleteExpense(int id) throws Exception;

	public double getMostRecentAmountForExpense(int expenseId) throws Exception;

}
