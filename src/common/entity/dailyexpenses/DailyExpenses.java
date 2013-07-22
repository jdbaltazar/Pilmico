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

import common.entity.inventorysheet.InventorySheetData;
import common.entity.profile.Account;

@Entity
public class DailyExpenses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "daily_expenses_type")
	private DailyExpensesType dailyExpensesType;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account")
	private Account account;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "expense", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DailyExpensesDetail> dailyExpenseDetails = new HashSet<DailyExpensesDetail>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheetData inventorySheetData;

	public DailyExpenses() {
		super();
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account, boolean valid, String remarks,
			Set<DailyExpensesDetail> dailyExpenseDetails) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = valid;
		this.remarks = remarks;
		this.dailyExpenseDetails = dailyExpenseDetails;
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account, boolean valid, String remarks) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = valid;
		this.remarks = remarks;
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account, boolean valid) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = valid;
	}

	public DailyExpenses(Date date, DailyExpensesType dailyExpensesType, Account account) {
		super();
		this.date = date;
		this.dailyExpensesType = dailyExpensesType;
		this.account = account;
		this.valid = true;
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

	public Set<DailyExpensesDetail> getDailyExpenseDetails() {
		return dailyExpenseDetails;
	}

	public void setDailyExpenseDetails(Set<DailyExpensesDetail> dailyExpenseDetails) {
		this.dailyExpenseDetails = dailyExpenseDetails;
	}

	public void addDailyExpenseDetail(DailyExpensesDetail expenseDetail) {
		expenseDetail.setDailyExpenses(this);
		dailyExpenseDetails.add(expenseDetail);
	}

	public void removeDailyExpenseDetail(DailyExpensesDetail expenseDetail) {
		removeDailyExpenseDetail(expenseDetail.getId());
	}

	public void removeDailyExpenseDetail(int expenseDetailId) {
		for (DailyExpensesDetail expenseDetail : dailyExpenseDetails) {
			if (expenseDetail.getId() == expenseDetailId) {
				dailyExpenseDetails.remove(expenseDetail);
				break;
			}
		}
	}

	public double getDailyExpensesAmount() {
		double total = 0;
		for (DailyExpensesDetail ded : dailyExpenseDetails) {
			total += ded.getAmount();
		}
		return total;
	}

	public InventorySheetData getInventorySheetData() {
		return inventorySheetData;
	}

	public void setInventorySheetData(InventorySheetData inventorySheetData) {
		this.inventorySheetData = inventorySheetData;
	}

	public String toString() {
		return id + "";
	}

}
