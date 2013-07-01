package common.entity.accountreceivable;

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

import common.entity.profile.Account;
import common.entity.profile.Person;

@Entity
public class ARPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_receivable_id")
	private AccountReceivable accountReceivable;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_representative")
	private Person representative;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@Column
	private boolean accounted;

	public ARPayment() {
		super();
	}

	public ARPayment(AccountReceivable accountReceivable, Date date, double amount, Person representative, Account issuedBy, boolean valid,
			String remarks, boolean accounted) {
		super();
		this.accountReceivable = accountReceivable;
		this.date = date;
		this.amount = amount;
		this.representative = representative;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
	}

	public ARPayment(AccountReceivable accountReceivable, Date date, double amount, Account issuedBy, boolean valid, boolean accounted) {
		super();
		this.accountReceivable = accountReceivable;
		this.date = date;
		this.amount = amount;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.accounted = accounted;
	}

	public ARPayment(AccountReceivable accountReceivable, Date date, double amount, Account issuedBy) {
		super();
		this.accountReceivable = accountReceivable;
		this.date = date;
		this.amount = amount;
		this.issuedBy = issuedBy;
		this.valid = true;
		this.accounted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccountReceivable getAccountReceivable() {
		return accountReceivable;
	}

	public void setAccountReceivable(AccountReceivable accountReceivable) {
		this.accountReceivable = accountReceivable;
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

	public Person getRepresentative() {
		return representative;
	}

	public void setRepresentative(Person representative) {
		this.representative = representative;
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

	public boolean isAccounted() {
		return accounted;
	}

	public void setAccounted(boolean accounted) {
		this.accounted = accounted;
	}

}