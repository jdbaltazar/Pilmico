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

import common.entity.profile.Account;
import common.entity.profile.Employee;

@Entity
public class CashAdvance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private Date date;

	@Column
	private boolean paid;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_for")
	private Employee issuedFor;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private String remarks;

	@Column
	private boolean valid;

	public CashAdvance() {
		super();
	}

	public CashAdvance(Date date, boolean paid, double amount, Employee issuedFor, Account issuedBy, boolean valid) {
		super();
		this.date = date;
		this.paid = paid;
		this.amount = amount;
		this.issuedFor = issuedFor;
		this.issuedBy = issuedBy;
		this.valid = valid;
	}

	public CashAdvance(Date date, boolean paid, double amount, Employee issuedFor, Account issuedBy, String remarks, boolean valid) {
		super();
		this.date = date;
		this.paid = paid;
		this.amount = amount;
		this.issuedFor = issuedFor;
		this.issuedBy = issuedBy;
		this.remarks = remarks;
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

	public Employee getIssuedFor() {
		return issuedFor;
	}

	public void setIssuedFor(Employee issuedFor) {
		this.issuedFor = issuedFor;
	}

	public Account getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(Account issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
