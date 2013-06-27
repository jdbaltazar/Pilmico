package common.entity.dailyexpenses;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import common.entity.profile.Account;

@Entity
public class DailyExpenses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "daily_expense_type")
	private DailyExpensesType dailyExpensesType;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account")
	private Account account;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@Column
	private boolean accounted;

	@OneToMany(mappedBy = "expense", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DailyExpensesDetail> dailyExpenseDetails = new HashSet<DailyExpensesDetail>();

	public DailyExpenses() {
		super();
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account, boolean valid, String remarks, boolean accounted,
			Set<DailyExpensesDetail> dailyExpenseDetails) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
		this.dailyExpenseDetails = dailyExpenseDetails;
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account, boolean valid, String remarks, boolean accounted) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account, boolean valid, boolean accounted) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = valid;
		this.accounted = accounted;
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = true;
		this.accounted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public DailyExpensesType getExpenseType() {
		return dailyExpensesType;
	}

	public void setExpenseType(DailyExpensesType expenseType) {
		this.dailyExpensesType = expenseType;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DailyExpensesType getDailyExpensesType() {
		return dailyExpensesType;
	}

	public void setDailyExpensesType(DailyExpensesType dailyExpensesType) {
		this.dailyExpensesType = dailyExpensesType;
	}

	public boolean isAccounted() {
		return accounted;
	}

	public void setAccounted(boolean accounted) {
		this.accounted = accounted;
	}

	public Set<DailyExpensesDetail> getDailyExpenseDetails() {
		return dailyExpenseDetails;
	}

	public void setDailyExpenseDetails(Set<DailyExpensesDetail> dailyExpenseDetails) {
		this.dailyExpenseDetails = dailyExpenseDetails;
	}

	public Set<DailyExpensesDetail> getExpenseDetails() {
		return dailyExpenseDetails;
	}

	public void setExpenseDetails(Set<DailyExpensesDetail> expenseDetails) {
		this.dailyExpenseDetails = expenseDetails;
	}

	public void addExpenseDetail(DailyExpensesDetail expenseDetail) {
		expenseDetail.setDailyExpenses(this);
		dailyExpenseDetails.add(expenseDetail);
	}

	public void removeExpenseDetail(DailyExpensesDetail expenseDetail) {
		for (DailyExpensesDetail d : dailyExpenseDetails) {
			if (d.equals(expenseDetail))
				dailyExpenseDetails.remove(d);
		}
	}

	public void removeExpenseDetail(int expenseDetailId) {
		for (DailyExpensesDetail expenseDetail : dailyExpenseDetails) {
			if (expenseDetail.getId() == expenseDetailId)
				dailyExpenseDetails.remove(expenseDetail);
		}
	}

}
