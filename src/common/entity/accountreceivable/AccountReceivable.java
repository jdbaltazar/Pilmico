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

import common.entity.inventorysheet.InventorySheetData;
import common.entity.profile.Account;
import common.entity.profile.Person;

@Entity
public class AccountReceivable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Person customer;

	@Column
	private double amount;

	@Column
	private double balance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "accountReceivable", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AccountReceivableDetail> accountReceivableDetails = new HashSet<AccountReceivableDetail>();

	@OneToMany(mappedBy = "accountReceivable", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ARPayment> arPayments = new HashSet<ARPayment>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheetData inventorySheetData;

	public AccountReceivable() {
		super();
	}

	public AccountReceivable(Date date, Person customer, double amount, double balance, Account issuedBy, boolean valid, String remarks,
			Set<AccountReceivableDetail> accountReceivableDetails) {
		super();
		this.date = date;
		this.customer = customer;
		this.amount = amount;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		setAccountReceivableDetails(accountReceivableDetails);
	}

	public AccountReceivable(Date date, Person customer, double amount, double balance, Account issuedBy, boolean valid, String remarks) {
		super();
		this.date = date;
		this.customer = customer;
		this.amount = amount;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
	}

	public AccountReceivable(Date date, Person customer, double amount, double balance, Account issuedBy, boolean valid) {
		super();
		this.date = date;
		this.customer = customer;
		this.amount = amount;
		this.balance = balance;
		this.issuedBy = issuedBy;
		this.valid = valid;
	}

	public AccountReceivable(Date date, Person customer, Account issuedBy) {
		super();
		this.date = date;
		this.customer = customer;
		this.amount = 0d;
		this.balance = 0d;
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

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public double getAmount() {
		double total = 0;
		if (accountReceivableDetails.size() > 0) {
			for (AccountReceivableDetail ard : accountReceivableDetails) {
				total += ((ard.getPricePerSack() * ard.getQuantityInSack()) + (ard.getPricePerKilo() * ard.getQuantityInKilo()));
			}
		} else {
			total += amount;
		}
		return total;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public void setAccountReceivableDetails(Set<AccountReceivableDetail> accountReceivableDetails) {
		for (AccountReceivableDetail ard : accountReceivableDetails) {
			addAccountReceivableDetail(ard);
		}
	}

	public Set<AccountReceivableDetail> getAccountReceivableDetails() {
		return accountReceivableDetails;
	}

	// public double getAccountReceivablesAmount() {
	// double total = 0;
	// if (accountReceivableDetails.size() > 0) {
	// for (AccountReceivableDetail ard : accountReceivableDetails) {
	// total += ((ard.getPricePerSack() * ard.getQuantityInSack()) +
	// (ard.getPricePerKilo() * ard.getQuantityInKilo()));
	// }
	// } else {
	// total += balance;
	// }
	// return total;
	// }

	// public void setAccountReceivableDetails(Set<AccountReceivableDetail>
	// accountReceivableDetails) {
	// this.accountReceivableDetails = accountReceivableDetails;
	// // increment the balance
	// for (AccountReceivableDetail ard : accountReceivableDetails) {
	// balance += ((ard.getPricePerSack() * ard.getQuantityInSack()) +
	// (ard.getPricePerKilo() * ard.getQuantityInKilo()));
	// }
	// }

	public void addAccountReceivableDetail(AccountReceivableDetail accountReceivableDetail) {
		accountReceivableDetail.setAccountReceivable(this);
		accountReceivableDetails.add(accountReceivableDetail);
		balance += ((accountReceivableDetail.getPricePerSack() * accountReceivableDetail.getQuantityInSack()) + (accountReceivableDetail
				.getPricePerKilo() * accountReceivableDetail.getQuantityInKilo()));

	}

	public void removeAccountReceivableDetail(AccountReceivableDetail accountReceivableDetail) {
		removeAccountReceivableDetail(accountReceivableDetail.getId());
	}

	public void removeAccountReceivableDetail(int accountReceivableDetailId) {
		for (AccountReceivableDetail ard : accountReceivableDetails) {
			if (ard.getId() == accountReceivableDetailId) {
				accountReceivableDetails.remove(ard);
				balance -= ((ard.getPricePerSack() * ard.getQuantityInSack()) + (ard.getPricePerKilo() * ard.getQuantityInKilo()));
			}
		}

	}

	public Set<ARPayment> getAllArPayments() {
		return arPayments;
	}

	public Set<ARPayment> getValidArPayments() {
		Set<ARPayment> validArPayments = new HashSet<ARPayment>();
		for (ARPayment arp : arPayments) {
			if (arp.isValid()) {
				validArPayments.add(arp);
			}
		}
		return validArPayments;
	}

	Set<ARPayment> getInvalidArPayment() {
		Set<ARPayment> inValidArPayments = new HashSet<ARPayment>();
		for (ARPayment arp : inValidArPayments) {
			if (!arp.isValid()) {
				inValidArPayments.add(arp);
			}
		}
		return inValidArPayments;
	}

	//
	// public void setArPayments(Set<ARPayment> arPayments) {
	// this.arPayments = arPayments;
	// }

	public void addARPayment(ARPayment arPayment) {
		arPayment.setAccountReceivable(this);
		arPayments.add(arPayment);
		balance -= (arPayment.getAmount());
	}

	public void removeARPayment(ARPayment arPayment) {
		removeARPayment(arPayment.getId());
	}

	public void removeARPayment(int arPaymentId) {
		for (ARPayment arp : arPayments) {
			if (arp.getId() == arPaymentId) {
				accountReceivableDetails.remove(arp);
				balance -= (arp.getAmount());
				break;
			}
		}
	}

	public double getARPaymentsAmount() {
		double total = 0d;
		Set<ARPayment> arps = getValidArPayments();
		for (ARPayment arp : arps) {
			total += arp.getAmount();
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
		return "" + id;
	}

}
