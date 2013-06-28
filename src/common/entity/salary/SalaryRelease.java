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

import common.entity.profile.Account;
import common.entity.profile.Employee;

@Entity
public class SalaryRelease {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_for")
	private Employee issuedFor;

	@Column
	private double amount;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@Column
	private boolean accounted;

	@OneToMany(mappedBy = "salaryRelease", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FeeDeduction> feeDeductions = new HashSet<FeeDeduction>();

	@OneToMany(mappedBy = "salaryRelease", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CADeduction> caDeductions = new HashSet<CADeduction>();

	public SalaryRelease() {
		super();
	}

	public SalaryRelease(Date date, Employee issuedFor, double amount, Account issuedBy, boolean valid, String remarks, boolean accounted,
			Set<FeeDeduction> feeDeductions, Set<CADeduction> caDeductions) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.amount = amount;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
		this.feeDeductions = feeDeductions;
		this.caDeductions = caDeductions;
	}

	public SalaryRelease(Date date, Employee issuedFor, double amount, Account issuedBy, boolean valid, String remarks, boolean accounted) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.amount = amount;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
	}

	public SalaryRelease(Date date, Employee issuedFor, double amount, Account issuedBy, boolean valid, boolean accounted) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
		this.amount = amount;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.accounted = accounted;
	}

	public SalaryRelease(Date date, Employee issuedFor, double amount, Account issuedBy) {
		super();
		this.date = date;
		this.issuedFor = issuedFor;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public Set<CADeduction> getCaDeductions() {
		return caDeductions;
	}

	public void setCaDeductions(Set<CADeduction> caDeductions) {
		this.caDeductions = caDeductions;
	}

	public void addCADeduction(CADeduction caDeduction) {
		caDeduction.setSalaryRelease(this);
		caDeductions.add(caDeduction);
	}

	public void removeCADeduction(CADeduction caDeduction) {
		removeCADeduction(caDeduction.getId());
	}

	public void removeCADeduction(int caDeductionId) {
		for (CADeduction cad : caDeductions) {
			if (cad.getId() == caDeductionId) {
				caDeductions.remove(cad);
				break;
			}
		}
	}

}
