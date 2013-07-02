package common.entity.salary;

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

import common.entity.inventorysheet.InventorySheet;
import common.entity.profile.Account;
import common.entity.profile.Employee;

@Entity
public class CashAdvance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private boolean paid;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column
	private double balance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "cashAdvance", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CADeduction> caDeductions = new HashSet<CADeduction>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheet inventorySheet;

	public CashAdvance() {
		super();
	}

	public CashAdvance(Date date, boolean paid, double amount, Employee employee, double balance, Account issuedBy, boolean valid, String remarks,
			Set<CADeduction> caDeductions) {
		super();
		this.date = date;
		this.paid = paid;
		this.amount = amount;
		this.employee = employee;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.caDeductions = caDeductions;
	}

	public CashAdvance(Date date, boolean paid, double amount, Employee employee, double balance, Account issuedBy, boolean valid, String remarks,
			boolean accounted) {
		super();
		this.date = date;
		this.paid = paid;
		this.amount = amount;
		this.employee = employee;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
	}

	public CashAdvance(Date date, boolean paid, double amount, Employee employee, double balance, Account issuedBy, boolean valid, boolean accounted) {
		super();
		this.date = date;
		this.paid = paid;
		this.amount = amount;
		this.employee = employee;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
	}

	public CashAdvance(Date date, boolean paid, double amount, Employee employee, double balance, Account issuedBy) {
		super();
		this.date = date;
		this.paid = paid;
		this.amount = amount;
		this.employee = employee;
		this.balance = balance;
		this.issuedBy = issuedBy;
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

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public Set<CADeduction> getCaDeductions() {
		return caDeductions;
	}

	public void setCaDeductions(Set<CADeduction> caDeductions) {
		this.caDeductions = caDeductions;
	}

	public InventorySheet getInventorySheet() {
		return inventorySheet;
	}

	public void setInventorySheet(InventorySheet inventorySheet) {
		this.inventorySheet = inventorySheet;
	}

	public String toString() {
		return "" + id;
	}

}
