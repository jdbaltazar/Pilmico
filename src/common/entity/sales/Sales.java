package common.entity.sales;

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
import common.entity.profile.Person;

@Entity(name = "Sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Person customer;

	@Column(name = "rc_no")
	private String rcNo;

	@Column(name = "issued_at")
	private String issuedAt;

	@Column(name = "issued_on")
	@Temporal(TemporalType.DATE)
	private Date issuedOn;

	@Column(name = "receipt_no")
	private String receiptNo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cashier")
	private Account cashier;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "sales", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SalesDetail> salesDetails = new HashSet<SalesDetail>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheet inventorySheet;

	public Sales() {
		super();
	}

	public Sales(Date date, Person customer, String rcNo, String issuedAt, Date issuedOn, String receiptNo, Account cashier, boolean valid,
			String remarks, Set<SalesDetail> salesDetails) {
		super();
		this.date = date;
		this.customer = customer;
		this.rcNo = rcNo;
		this.issuedAt = issuedAt;
		this.issuedOn = issuedOn;
		this.receiptNo = receiptNo;
		this.cashier = cashier;
		this.valid = valid;
		this.remarks = remarks;
		this.salesDetails = salesDetails;
	}

	public Sales(Date date, Person customer, String rcNo, String issuedAt, Date issuedOn, String receiptNo, Account cashier, boolean valid,
			String remarks) {
		super();
		this.date = date;
		this.customer = customer;
		this.rcNo = rcNo;
		this.issuedAt = issuedAt;
		this.issuedOn = issuedOn;
		this.receiptNo = receiptNo;
		this.cashier = cashier;
		this.valid = valid;
		this.remarks = remarks;
	}

	public Sales(Date date, Person customer, String rcNo, String issuedAt, Date issuedOn, String receiptNo, Account cashier) {
		super();
		this.date = date;
		this.customer = customer;
		this.rcNo = rcNo;
		this.issuedAt = issuedAt;
		this.issuedOn = issuedOn;
		this.receiptNo = receiptNo;
		this.cashier = cashier;
		this.valid = true;
	}

	public Sales(Date date, Account cashier, boolean valid) {
		super();
		this.date = date;
		this.cashier = cashier;
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Set<SalesDetail> getSalesDetails() {
		return salesDetails;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public String getRcNo() {
		return rcNo;
	}

	public void setRcNo(String rcNo) {
		this.rcNo = rcNo;
	}

	public String getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Account getCashier() {
		return cashier;
	}

	public void setCashier(Account cashier) {
		this.cashier = cashier;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setSalesDetails(Set<SalesDetail> salesDetails) {
		this.salesDetails = salesDetails;
	}

	public void addSalesDetail(SalesDetail salesDetail) {
		salesDetail.setSales(this);
		salesDetails.add(salesDetail);
	}

	public void removeSalesDetail(SalesDetail salesDetail) {
		removeSalesDetail(salesDetail.getId());
	}

	public void removeSalesDetail(int salesDetailId) {
		for (SalesDetail sal : salesDetails) {
			if (sal.getId() == salesDetailId) {
				salesDetails.remove(sal);
				break;
			}
		}
	}

	public double getSalesAmount() {
		double total = 0;
		for (SalesDetail sd : salesDetails) {
			total += ((sd.getPricePerSack() * sd.getQuantityInSack()) + (sd.getPricePerKilo() * sd.getQuantityInKilo()));
		}
		return total;

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
