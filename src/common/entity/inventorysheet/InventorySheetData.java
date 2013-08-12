package common.entity.inventorysheet;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.profile.Account;
import common.entity.pullout.PullOut;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;

@Entity
@Table(name = "InventorySheet")
public class InventorySheetData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "previous_acoh")
	private double previousAcoh;

	@Column(name = "over")
	private double overAmount;

	@Column(name = "short")
	private double shortAmount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<InventorySheetDataDetail> inventorySheetDataDetails = new HashSet<InventorySheetDataDetail>();

	@OneToOne(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Breakdown breakdown;

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Delivery> deliveries = new HashSet<Delivery>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PullOut> pullouts = new HashSet<PullOut>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Sales> sales = new HashSet<Sales>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AccountReceivable> accountReceivables = new HashSet<AccountReceivable>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DiscountIssue> discountIssues = new HashSet<DiscountIssue>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ARPayment> arPayments = new HashSet<ARPayment>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CAPayment> caPayments = new HashSet<CAPayment>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DailyExpenses> dailyExpenses = new HashSet<DailyExpenses>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CashAdvance> cashAdvances = new HashSet<CashAdvance>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SalaryRelease> salaryReleases = new HashSet<SalaryRelease>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Deposit> deposits = new HashSet<Deposit>();

	public InventorySheetData() {
		super();
	}

	public InventorySheetData(Date date, double previousAcoh, double overAmount, double shortAmount, Account issuedBy, String remarks,
			Breakdown breakdown, Set<InventorySheetDataDetail> inventorySheetDataDetails) {
		super();
		this.date = date;
		this.previousAcoh = previousAcoh;
		this.overAmount = overAmount;
		this.shortAmount = shortAmount;
		this.issuedBy = issuedBy;
		this.remarks = remarks;
		this.inventorySheetDataDetails = inventorySheetDataDetails;
	}

	public InventorySheetData(Date date, double previousAcoh, double overAmount, double shortAmount, Account issuedBy, String remarks) {
		super();
		this.date = date;
		this.previousAcoh = previousAcoh;
		this.overAmount = overAmount;
		this.shortAmount = shortAmount;
		this.issuedBy = issuedBy;
		this.remarks = remarks;
	}

	public InventorySheetData(Date date, double previousAcoh, double overAmount, double shortAmount, Account issuedBy) {
		super();
		this.date = date;
		this.previousAcoh = previousAcoh;
		this.overAmount = overAmount;
		this.shortAmount = shortAmount;
		this.issuedBy = issuedBy;
	}

	public InventorySheetData(Set<Delivery> deliveries, Set<PullOut> pullouts, Set<Sales> sales, Set<AccountReceivable> accountReceivables,
			Set<DiscountIssue> discountIssues, Set<ARPayment> arPayments, Set<CAPayment> caPayments, Set<DailyExpenses> dailyExpenses,
			Set<CashAdvance> cashAdvances, Set<SalaryRelease> salaryReleases, Set<Deposit> deposits) {
		super();
		this.deliveries = deliveries;
		this.pullouts = pullouts;
		this.sales = sales;
		this.accountReceivables = accountReceivables;
		this.discountIssues = discountIssues;
		this.arPayments = arPayments;
		this.caPayments = caPayments;
		this.dailyExpenses = dailyExpenses;
		this.cashAdvances = cashAdvances;
		this.salaryReleases = salaryReleases;
		this.deposits = deposits;
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

	public double getPreviousAcoh() {
		return previousAcoh;
	}

	public void setPreviousAcoh(double previousAcoh) {
		this.previousAcoh = previousAcoh;
	}

	public double getOverAmount() {
		return overAmount;
	}

	public void setOverAmount(double overAmount) {
		this.overAmount = overAmount;
	}

	public double getShortAmount() {
		return shortAmount;
	}

	public void setShortAmount(double shortAmount) {
		this.shortAmount = shortAmount;
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

	public Set<InventorySheetDataDetail> getInventorySheetDataDetails() {
		return inventorySheetDataDetails;
	}

	public void setInventorySheetDataDetails(Set<InventorySheetDataDetail> inventorySheetDataDetails) {
		this.inventorySheetDataDetails = inventorySheetDataDetails;
	}

	public void addInventorySheetDataDetail(InventorySheetDataDetail inventorySheetDataDetail) {
		inventorySheetDataDetail.setInventorySheetData(this);
		inventorySheetDataDetails.add(inventorySheetDataDetail);
	}

	public void removeInventorySheetDataDetail(InventorySheetDataDetail inventorySheetDataDetail) {
		removeInventorySheetDataDetail(inventorySheetDataDetail.getId());
	}

	public void removeInventorySheetDataDetail(int inventorySheetDataDetailId) {
		for (InventorySheetDataDetail isdd : inventorySheetDataDetails) {
			if (isdd.getId() == inventorySheetDataDetailId) {
				inventorySheetDataDetails.remove(isdd);
				break;
			}
		}
	}

	public Set<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Set<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public void finalizeDeliveries() {
		for (Delivery d : deliveries) {
			d.setInventorySheetData(this);
		}
	}

	public void addDelivery(Delivery delivery) {
		// delivery.setInventorySheetData(this);
		deliveries.add(delivery);
	}

	public void removeDelivery(Delivery delivery) {
		removeSales(delivery.getId());
	}

	public void removeDelivery(int deliveryId) {
		for (Delivery d : deliveries) {
			if (d.getId() == deliveryId) {
				deliveries.remove(d);
				break;
			}
		}
	}

	public Set<PullOut> getPullouts() {
		return pullouts;
	}

	public void setPullouts(Set<PullOut> pullouts) {
		this.pullouts = pullouts;
		for (PullOut po : pullouts) {
			po.setInventorySheetData(this);
		}
	}

	public void finalizePullouts() {
		for (PullOut po : pullouts) {
			po.setInventorySheetData(this);
		}
	}

	public void addPullOut(PullOut pullOut) {
		// pullOut.setInventorySheetData(this);
		pullouts.add(pullOut);
	}

	public void removePullOut(PullOut pullOut) {
		removeSales(pullOut.getId());
	}

	public void removePullOut(int pullOutId) {
		for (PullOut p : pullouts) {
			if (p.getId() == pullOutId) {
				pullouts.remove(p);
				break;
			}
		}
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	public void finalizeSales() {
		for (Sales s : sales) {
			s.setInventorySheetData(this);
		}
	}

	public void addSales(Sales s) {
		// s.setInventorySheetData(this);
		sales.add(s);
	}

	public void removeSales(Sales s) {
		removeSales(s.getId());
	}

	public void removeSales(int salesId) {
		for (Sales s : sales) {
			if (s.getId() == salesId) {
				sales.remove(s);
				break;
			}
		}
	}

	public Set<AccountReceivable> getAccountReceivables() {
		return accountReceivables;
	}

	public void setAccountReceivables(Set<AccountReceivable> accountReceivables) {
		this.accountReceivables = accountReceivables;
	}

	public void finalizeAccountReceivables() {
		for (AccountReceivable ar : accountReceivables) {
			ar.setInventorySheetData(this);
		}
	}

	public void addAccountReceivable(AccountReceivable accountReceivable) {
		// accountReceivable.setInventorySheetData(this);
		accountReceivables.add(accountReceivable);
	}

	public void removeAccountReceivable(AccountReceivable accountReceivable) {
		removeSales(accountReceivable.getId());
	}

	public void removeAccountReceivable(int accountReceivableId) {
		for (AccountReceivable ar : accountReceivables) {
			if (ar.getId() == accountReceivableId) {
				accountReceivables.remove(ar);
				break;
			}
		}
	}

	public Set<DiscountIssue> getDiscountIssues() {
		return discountIssues;
	}

	public void setDiscountIssues(Set<DiscountIssue> discountIssues) {
		this.discountIssues = discountIssues;
	}

	public void finalizeDiscountIssues() {
		for (DiscountIssue di : discountIssues) {
			di.setInventorySheetData(this);
		}
	}

	public Set<ARPayment> getArPayments() {
		return arPayments;
	}

	public void setArPayments(Set<ARPayment> arPayments) {
		this.arPayments = arPayments;
	}

	public void finalizeArPayments() {
		for (ARPayment ar : arPayments) {
			ar.setInventorySheetData(this);
		}
	}

	public Set<CAPayment> getCaPayments() {
		return caPayments;
	}

	public void setCaPayments(Set<CAPayment> caPayments) {
		this.caPayments = caPayments;
	}

	public void finalizeCaPayments() {
		for (CAPayment ca : caPayments) {
			ca.setInventorySheetData(this);
		}
	}

	public Set<DailyExpenses> getDailyExpenses() {
		return dailyExpenses;
	}

	public void setDailyExpenses(Set<DailyExpenses> dailyExpenses) {
		this.dailyExpenses = dailyExpenses;
	}

	public void finalizeDailyExpenses() {
		for (DailyExpenses d : dailyExpenses) {
			d.setInventorySheetData(this);
		}
	}

	public Set<CashAdvance> getCashAdvances() {
		return cashAdvances;
	}

	public void setCashAdvances(Set<CashAdvance> cashAdvances) {
		this.cashAdvances = cashAdvances;

	}

	public void finalizeCashAdvances() {
		for (CashAdvance ca : cashAdvances) {
			ca.setInventorySheetData(this);
		}
	}

	public Set<SalaryRelease> getSalaryReleases() {
		return salaryReleases;
	}

	public void setSalaryReleases(Set<SalaryRelease> salaryReleases) {
		this.salaryReleases = salaryReleases;

	}

	public void finalizeSalaryReleases() {
		for (SalaryRelease sr : salaryReleases) {
			sr.setInventorySheetData(this);
		}
	}

	public Set<Deposit> getDeposits() {
		return deposits;
	}

	public void setDeposits(Set<Deposit> deposits) {
		this.deposits = deposits;
	}

	public void finalizeDeposits() {
		for (Deposit d : deposits) {
			d.setInventorySheetData(this);
		}
	}

	public void setBreakdown(Breakdown breakdown) {
		this.breakdown = breakdown;
	}

	public void finalize() {
		finalizeDeliveries();
		finalizePullouts();
		finalizeSales();
		finalizeAccountReceivables();
		finalizeDiscountIssues();
		finalizeArPayments();
		finalizeCaPayments();
		finalizeDailyExpenses();
		finalizeCashAdvances();
		finalizeSalaryReleases();
		finalizeDeposits();
	}

	public Breakdown getBreakdown() {
		return breakdown;
	}

	public double getActualCashCount() {
		if (breakdown != null)
			return breakdown.getActualCashCount();
		return 0d;
	}

}
