package common.entity.accountreceivable;

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
import common.entity.profile.Person;

@Entity
public class AccountReceivable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Person customer;

	@Column
	private double balance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@Column
	private boolean accounted;

	@OneToMany(mappedBy = "accountReceivable", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AccountReceivableDetail> accountReceivableDetails = new HashSet<AccountReceivableDetail>();

	@OneToMany(mappedBy = "accountReceivable", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ARPayment> arPayments = new HashSet<ARPayment>();

	public AccountReceivable() {
		super();
	}

	public AccountReceivable(Date date, Person customer, double balance, Account issuedBy, boolean valid, String remarks, boolean accounted,
			Set<AccountReceivableDetail> accountReceivableDetails, Set<ARPayment> arPayments) {
		super();
		this.date = date;
		this.customer = customer;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
		this.accountReceivableDetails = accountReceivableDetails;
		this.arPayments = arPayments;
	}

	public AccountReceivable(Date date, Person customer, double balance, Account issuedBy, boolean valid, String remarks, boolean accounted) {
		super();
		this.date = date;
		this.customer = customer;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
	}

	public AccountReceivable(Date date, Person customer, double balance, Account issuedBy, boolean valid, boolean accounted) {
		super();
		this.date = date;
		this.customer = customer;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.accounted = accounted;
	}

	public AccountReceivable(Date date, Person customer, double balance, Account issuedBy) {
		super();
		this.date = date;
		this.customer = customer;
		this.balance = balance;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
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

	public boolean isAccounted() {
		return accounted;
	}

	public void setAccounted(boolean accounted) {
		this.accounted = accounted;
	}

	public Set<AccountReceivableDetail> getAccountReceivableDetails() {
		return accountReceivableDetails;
	}

	public void setAccountReceivableDetails(Set<AccountReceivableDetail> accountReceivableDetails) {
		this.accountReceivableDetails = accountReceivableDetails;
	}

	public void addAccountReceivableDetail(AccountReceivableDetail accountReceivableDetail) {
		accountReceivableDetail.setAccountReceivable(this);
		accountReceivableDetails.add(accountReceivableDetail);
	}

	public void removeAccountReceivableDetail(AccountReceivableDetail accountReceivableDetail) {
		removeAccountReceivableDetail(accountReceivableDetail.getId());
	}

	public void removeAccountReceivableDetail(int accountReceivableDetailId) {
		for (AccountReceivableDetail ard : accountReceivableDetails) {
			if (ard.getId() == accountReceivableDetailId)
				accountReceivableDetails.remove(ard);
		}
	}

	public Set<ARPayment> getArPayments() {
		return arPayments;
	}

	public void setArPayments(Set<ARPayment> arPayments) {
		this.arPayments = arPayments;
	}

	public void addARPayment(ARPayment arPayment) {
		arPayment.setAccountReceivable(this);
		arPayments.add(arPayment);
	}

	public void removeARPayment(ARPayment arPayment) {
		removeARPayment(arPayment.getId());
	}

	public void removeARPayment(int arPaymentId) {
		for (ARPayment arp : arPayments) {
			if (arp.getId() == arPaymentId) {
				accountReceivableDetails.remove(arp);
				break;
			}
		}
	}

	public String toString() {
		return "" + id;
	}

}
