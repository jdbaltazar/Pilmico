package common.entity.expense;

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
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "received_by")
	private Account account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "expense_type")
	private ExpenseType expenseType;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "expense", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExpenseDetail> expenseDetails = new HashSet<ExpenseDetail>();

	public Expense() {
		super();
	}

	public Expense(Date date, Account account, ExpenseType expenseType, boolean valid) {
		super();
		this.date = date;
		this.account = account;
		this.expenseType = expenseType;
		this.valid = valid;
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

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
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

	public Set<ExpenseDetail> getExpenseDetails() {
		return expenseDetails;
	}

	public void setExpenseDetails(Set<ExpenseDetail> expenseDetails) {
		this.expenseDetails = expenseDetails;
	}

	public void addExpenseDetail(ExpenseDetail expenseDetail) {
		expenseDetail.setExpense(this);
		expenseDetails.add(expenseDetail);
	}

	public void removeExpenseDetail(ExpenseDetail expenseDetail) {
		for (ExpenseDetail d : expenseDetails) {
			if (d.equals(expenseDetail))
				expenseDetails.remove(d);
		}
	}

	public void removeExpenseDetail(int expenseDetailId) {
		for (ExpenseDetail expenseDetail : expenseDetails) {
			if (expenseDetail.getId() == expenseDetailId)
				expenseDetails.remove(expenseDetail);
		}
	}

}
