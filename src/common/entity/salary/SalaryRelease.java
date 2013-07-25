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

import common.entity.cashadvance.CAPayment;
import common.entity.inventorysheet.InventorySheetData;
import common.entity.profile.Account;
import common.entity.profile.Employee;

@Entity
public class SalaryRelease {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_for")
	private Employee issuedFor;

	@Column(name = "amount")
	private double grossAmount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@OneToMany(mappedBy = "salaryRelease", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FeeDeduction> feeDeductions = new HashSet<FeeDeduction>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheetData inventorySheetData;

	public SalaryRelease() {
		super();
	}

	public SalaryRelease(Date date, Employee issuedFor, double grossAmount, Account issuedBy, boolean valid, String remarks,
			Set<FeeDeduction> feeDeductions, Set<CAPayment> caDeductions) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.grossAmount = grossAmount;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.feeDeductions = feeDeductions;
	}

	public SalaryRelease(Date date, Employee issuedFor, double grossAmount, Account issuedBy, boolean valid, String remarks) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.grossAmount = grossAmount;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
	}

	public SalaryRelease(Date date, Employee issuedFor, double grossAmount, Account issuedBy, boolean valid) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.grossAmount = grossAmount;
		this.issuedBy = issuedBy;
		this.valid = valid;
	}

	public SalaryRelease(Date date, Employee issuedFor, double grossAmount, Account issuedBy) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.grossAmount = grossAmount;
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

	public Employee getIssuedFor() {
		return issuedFor;
	}

	public void setIssuedFor(Employee issuedFor) {
		this.issuedFor = issuedFor;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public double getNetAmount() {
		return grossAmount - getTotalDeductions();
	}

	public double getTotalDeductions() {
		double total = 0d;
		for (FeeDeduction fd : feeDeductions) {
			total += fd.getAmount();
		}
		return total;
	}

	public void setAmount(double grossAmount) {
		this.grossAmount = grossAmount;
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

	public Set<FeeDeduction> getFeeDeductions() {
		return feeDeductions;
	}

	public void setFeeDeductions(Set<FeeDeduction> feeDeductions) {
		this.feeDeductions = feeDeductions;
	}

	public void addFeeDeduction(FeeDeduction feeDeduction) {
		feeDeduction.setSalaryRelease(this);
		feeDeductions.add(feeDeduction);
	}

	public void removeFeeDeduction(FeeDeduction feeDeduction) {
		removeFeeDeduction(feeDeduction.getId());
	}

	public void removeFeeDeduction(int feeDeductionId) {
		for (FeeDeduction fd : feeDeductions) {
			if (fd.getId() == feeDeductionId) {
				feeDeductions.remove(fd);
				break;
			}
		}
	}

	public InventorySheetData getInventorySheetData() {
		return inventorySheetData;
	}

	public void setInventorySheetData(InventorySheetData inventorySheetData) {
		this.inventorySheetData = inventorySheetData;
	}

	public String toString() {
		return id + "";
	}

}
