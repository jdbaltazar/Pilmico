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

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	@OneToMany(mappedBy = "pullOut", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PullOutDetail> pullOutDetails = new HashSet<PullOutDetail>();

	public PullOut() {
		super();
	}

	public PullOut(Date date, boolean valid, Account issuedBy) {
		super();
		this.date = date;
		this.valid = valid;
		this.issuedBy = issuedBy;
	}

	public PullOut(Date date, boolean valid, String remarks, Account issuedBy) {
		super();
		this.date = date;
		this.valid = valid;
		this.remarks = remarks;
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
		for (PullOutDetail pod : pullOutDetails) {
			if (pod.getId() == pullOutDetail.getId()) {
				pullOutDetails.remove(pod);
				break;
			}
		}
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
