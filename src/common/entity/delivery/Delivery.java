package common.entity.delivery;

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
import common.entity.store.Store;
import common.entity.supplier.Supplier;

@Entity
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "Date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "store")
	private Store store;

	@Column(name = "delivery_no")
	private String deliveryNo;

	@Column(name = "po_no")
	private String poNo;

	@Column
	private String terms;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "received_by")
	private Account receivedBy;

	@Column
	private boolean valid;

	@Column
	private String remarks;

	@Column
	private boolean accounted;

	@OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DeliveryDetail> deliveryDetails = new HashSet<DeliveryDetail>();

	public Delivery() {
		super();
	}

	public Delivery(Date date, Supplier supplier, Store store, String deliveryNo, String poNo, String terms, Account receivedBy, boolean valid,
			String remarks, boolean accounted, Set<DeliveryDetail> deliveryDetails) {
		super();
		this.date = date;
		this.supplier = supplier;
		this.store = store;
		this.deliveryNo = deliveryNo;
		this.poNo = poNo;
		this.terms = terms;
		this.receivedBy = receivedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
		this.deliveryDetails = deliveryDetails;
	}

	public Delivery(Date date, Supplier supplier, Store store, String deliveryNo, String poNo, String terms, Account receivedBy, boolean valid,
			String remarks, boolean accounted) {
		super();
		this.date = date;
		this.supplier = supplier;
		this.store = store;
		this.deliveryNo = deliveryNo;
		this.poNo = poNo;
		this.terms = terms;
		this.receivedBy = receivedBy;
		this.valid = valid;
		this.remarks = remarks;
		this.accounted = accounted;
	}

	public Delivery(Date date, Supplier supplier, Store store, String deliveryNo, String poNo, String terms, Account receivedBy) {
		super();
		this.date = date;
		this.supplier = supplier;
		this.store = store;
		this.deliveryNo = deliveryNo;
		this.poNo = poNo;
		this.terms = terms;
		this.receivedBy = receivedBy;
		this.valid = true;
		this.accounted = false;
	}

	public Delivery(Date date, Store store, Account receivedBy, boolean valid, boolean accounted) {
		super();
		this.date = date;
		this.store = store;
		this.receivedBy = receivedBy;
		this.valid = valid;
		this.accounted = accounted;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Account getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(Account receivedBy) {
		this.receivedBy = receivedBy;
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

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public boolean isAccounted() {
		return accounted;
	}

	public void setAccounted(boolean accounted) {
		this.accounted = accounted;
	}

	public Set<DeliveryDetail> getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(Set<DeliveryDetail> deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	public void addDEliveryDetail(DeliveryDetail deliveryDetail) {
		deliveryDetail.setDelivery(this);
		deliveryDetails.add(deliveryDetail);
	}

	public void removeDeliveryDetail(DeliveryDetail deliveryDetail) {
		for (DeliveryDetail d : deliveryDetails) {
			if (d.equals(deliveryDetail))
				deliveryDetails.remove(d);
		}
	}

	public void removeDeliveryDetail(int deliveryDetailId) {
		for (DeliveryDetail deliveryDetail : deliveryDetails) {
			if (deliveryDetail.getId() == deliveryDetailId)
				deliveryDetails.remove(deliveryDetail);
		}
	}

	public String toString() {
		return "" + id;
	}
}
