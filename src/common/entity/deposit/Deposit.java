package common.entity.deposit;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import common.entity.inventorysheet.InventorySheetData;
import common.entity.profile.Account;
import common.entity.profile.Employee;

@Entity
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "depositor")
	private Employee depositor;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheetData inventorySheet;

	public Deposit() {
		super();
	}

	public Deposit(Date date, BankAccount bankAccount, double amount, Employee depositor, Account issuedBy, boolean valid, String remarks) {
		super();
		this.date = date;
		this.bankAccount = bankAccount;
		this.amount = amount;
		this.depositor = depositor;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
	}

	public Deposit(Date date, BankAccount bankAccount, double amount, Employee depositor, Account issuedBy, boolean valid) {
		super();
		this.date = date;
		this.bankAccount = bankAccount;
		this.amount = amount;
		this.depositor = depositor;
		this.issuedBy = issuedBy;
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

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Employee getDepositor() {
		return depositor;
	}

	public void setDepositor(Employee depositor) {
		this.depositor = depositor;
	}

	public Account getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(Account issuedBy) {
		this.issuedBy = issuedBy;
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

	public InventorySheetData getInventorySheet() {
		return inventorySheet;
	}

	public void setInventorySheet(InventorySheetData inventorySheet) {
		this.inventorySheet = inventorySheet;
	}

	public String toString() {
		return id + "";
	}

}
