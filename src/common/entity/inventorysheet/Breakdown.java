package common.entity.inventorysheet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Breakdown {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private double coins;

	@Column
	private double check;

	@Column
	private String remarks;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheetData inventorySheet;

	public Breakdown() {
		super();
	}

	public Breakdown(double coins, double check, String remarks, InventorySheetData inventorySheet) {
		super();
		this.coins = coins;
		this.check = check;
		this.remarks = remarks;
		this.inventorySheet = inventorySheet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public double getCheck() {
		return check;
	}

	public void setCheck(double check) {
		this.check = check;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public InventorySheetData getInventorySheet() {
		return inventorySheet;
	}

	public void setInventorySheet(InventorySheetData inventorySheet) {
		this.inventorySheet = inventorySheet;
	}

}
