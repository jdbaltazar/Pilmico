package common.entity.pullout;

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

@Entity
public class PullOut {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@Column
	private boolean accounted;

	@OneToMany(mappedBy = "pullOut", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PullOutDetail> pullOutDetails = new HashSet<PullOutDetail>();

	public PullOut() {
		super();
	}

	public PullOut(Date date, Account issuedBy, boolean valid, String remarks, boolean accounted, Set<PullOutDetail> pullOutDetails) {
		super();
		this.date = date;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
		this.pullOutDetails = pullOutDetails;
	}

	public PullOut(Date date, Account issuedBy, boolean valid, String remarks, boolean accounted) {
		super();
		this.date = date;
		this.issuedBy = issuedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
	}

	public PullOut(Date date, Account issuedBy) {
		super();
		this.date = date;
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

	public Account getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(Account issuedBy) {
		this.issuedBy = issuedBy;
	}

	public boolean isAccounted() {
		return accounted;
	}

	public void setAccounted(boolean accounted) {
		this.accounted = accounted;
	}

	public Set<PullOutDetail> getPullOutDetails() {
		return pullOutDetails;
	}

	public void setPullOutDetails(Set<PullOutDetail> pullOutDetails) {
		this.pullOutDetails = pullOutDetails;
	}

	public void addPullOutDetail(PullOutDetail pullOutDetail) {
		pullOutDetail.setPullOut(this);
		pullOutDetails.add(pullOutDetail);
	}

	public void removePullOutDetail(PullOutDetail pullOutDetail) {
		removePullOutDetail(pullOutDetail.getId());
	}

	public void removePullOutDetail(int pullOutDetailId) {
		for (PullOutDetail pod : pullOutDetails) {
			if (pod.getId() == pullOutDetailId) {
				pullOutDetails.remove(pod);
				break;
			}
		}
	}

	public String toString() {
		return "" + id;
	}
}
