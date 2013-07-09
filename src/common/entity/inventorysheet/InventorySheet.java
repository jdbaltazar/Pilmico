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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.delivery.Delivery;
import common.entity.profile.Account;
import common.entity.pullout.PullOut;
import common.entity.sales.Sales;

@Entity
public class InventorySheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "previous_acoh")
	private double previousAcoh;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issued_by")
	private Account issuedBy;

	// @OneToMany(mappedBy = "inventorySheet", fetch = FetchType.EAGER, cascade =
	// CascadeType.ALL, orphanRemoval = true)
	// private Set<InventorySheetDetail> inventorySheetDetails = new
	// HashSet<InventorySheetDetail>();
	//
	// @OneToMany(mappedBy = "inventorySheet", fetch = FetchType.EAGER, cascade =
	// CascadeType.ALL, orphanRemoval = true)
	// private Set<Delivery> deliveries = new HashSet<Delivery>();
	//
	// @OneToMany(mappedBy = "inventorySheet", fetch = FetchType.EAGER, cascade =
	// CascadeType.ALL, orphanRemoval = true)
	// private Set<PullOut> pullouts = new HashSet<PullOut>();
	//
	// @OneToMany(mappedBy = "inventorySheet", fetch = FetchType.EAGER, cascade =
	// CascadeType.ALL, orphanRemoval = true)
	// private Set<Sales> sales = new HashSet<Sales>();
	//
	// @OneToMany(mappedBy = "inventorySheet", fetch = FetchType.EAGER, cascade =
	// CascadeType.ALL, orphanRemoval = true)
	// private Set<AccountReceivable> accountReceivables = new
	// HashSet<AccountReceivable>();

}
