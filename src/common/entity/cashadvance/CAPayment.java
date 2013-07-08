package common.entity.cashadvance;

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

import common.entity.inventorysheet.InventorySheet;
import common.entity.profile.Account;
import common.entity.profile.Person;

@Entity
public class CAPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cash_advance_id")
	private CashAdvance cashAdvance;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employee_representative")
	private Person employeeRepresentative;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheet inventorySheet;

	public CAPayment() {
		super();
	}

	public CAPayment(CashAdvance cashAdvance, Date date, double amount, Person employeeRepresentative, Account issuedBy, boolean valid, String remarks) {
		super();
		this.cashAdvance = cashAdvance;
		this.date = date;
		this.amount = amount;
		this.employeeRepresentative = employeeRepresentative;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
	}

	public CAPayment(CashAdvance cashAdvance, Date date, double amount, Account issuedBy, boolean valid) {
		super();
		this.cashAdvance = cashAdvance;
		this.date = date;
		this.amount = amount;
		this.issuedBy = issuedBy;
		this.valid = valid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CashAdvance getCashAdvance() {
		return cashAdvance;
	}

	public void setCashAdvance(CashAdvance cashAdvance) {
		this.cashAdvance = cashAdvance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Person getEmployeeRepresentative() {
		return employeeRepresentative;
	}

	public void setEmployeeRepresentative(Person employeeRepresentative) {
		this.employeeRepresentative = employeeRepresentative;
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

	public InventorySheet getInventorySheet() {
		return inventorySheet;
	}

	public void setInventorySheet(InventorySheet inventorySheet) {
		this.inventorySheet = inventorySheet;
	}

}
