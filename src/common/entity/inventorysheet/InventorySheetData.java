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

import common.entity.accountreceivable.AccountReceivable;
import common.entity.delivery.Delivery;
import common.entity.profile.Account;
import common.entity.pullout.PullOut;
import common.entity.sales.Sales;

@Entity
@Table(name = "InventorySheet")
public class InventorySheetData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
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

	@OneToOne(mappedBy = "inventorySheetData", fetch = FetchType.EAGER)
	private Breakdown breakdown;

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Delivery> deliveries = new HashSet<Delivery>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PullOut> pullouts = new HashSet<PullOut>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Sales> sales = new HashSet<Sales>();

	@OneToMany(mappedBy = "inventorySheetData", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AccountReceivable> accountReceivables = new HashSet<AccountReceivable>();

	// cash advance, ca payments, salary release, daily expenses, pulloout,
	// delivery, discount issue, ar, ar payments, sales, deposit

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

	public Set<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Set<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public Set<PullOut> getPullouts() {
		return pullouts;
	}

	public void setPullouts(Set<PullOut> pullouts) {
		this.pullouts = pullouts;
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	public Set<AccountReceivable> getAccountReceivables() {
		return accountReceivables;
	}

	public void setAccountReceivables(Set<AccountReceivable> accountReceivables) {
		this.accountReceivables = accountReceivables;
	}

	public Breakdown getBreakdown() {
		return breakdown;
	}

}
