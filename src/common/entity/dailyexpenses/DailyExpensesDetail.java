package common.entity.dailyexpenses;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DailyExpensesDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "daily_expenses_id")
	private DailyExpenses dailyExpenses;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "expense_id")
	private Expense expense;

	@Column
	private double amount;

	public DailyExpensesDetail() {
		super();
	}

	public DailyExpensesDetail(DailyExpenses dailyExpenses, Expense expense, double amount) {
		super();
		this.dailyExpenses = dailyExpenses;
		this.expense = expense;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DailyExpenses getDailyExpenses() {
		return dailyExpenses;
	}

	public void setDailyExpenses(DailyExpenses dailyExpenses) {
		this.dailyExpenses = dailyExpenses;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
