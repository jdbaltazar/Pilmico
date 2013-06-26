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

import common.entity.profile.Account;

@Entity(name = "Sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "Date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column
	private boolean valid;

	@Column
	private String remark;

	@OneToMany(mappedBy = "sales", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SalesDetail> salesDetails = new HashSet<SalesDetail>();

	public Sales() {
		super();
	}

	public Sales(Date date, Account account, boolean valid) {
		super();
		this.date = date;
		this.account = account;
		this.valid = valid;
	}

	public Sales(Date date, Account account, boolean valid, String remark) {
		super();
		this.date = date;
		this.account = account;
		this.valid = valid;
		this.remark = remark;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<SalesDetail> getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(Set<SalesDetail> salesDetails) {
		this.salesDetails = salesDetails;
	}

	public void addSalesDetail(SalesDetail salesDetail) {
		salesDetail.setSales(this);
		salesDetails.add(salesDetail);
	}

	public void removeSalesDetail(SalesDetail salesDetail) {
		for (SalesDetail sal : salesDetails) {
			if (sal.equals(salesDetail))
				salesDetails.remove(sal);
		}
	}

	public void removeSalesDetail(int salesDetailId) {
		for (SalesDetail sal : salesDetails) {
			if (sal.getId() == salesDetailId)
				salesDetails.remove(sal);
		}
	}

	public String toString() {
		return "" + id;
	}
}
