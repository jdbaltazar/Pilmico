package common.entity.inventorysheet;

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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "is_id")
	private InventorySheetData inventorySheetData;

	@OneToMany(mappedBy = "breakdown", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BreakdownLine> breakdownLines = new HashSet<BreakdownLine>();

	public Breakdown() {
		super();
	}

	public Breakdown(int id, double coins, double check, InventorySheetData inventorySheetData, Set<BreakdownLine> breakdownLines) {
		super();
		this.id = id;
		this.coins = coins;
		this.check = check;
		this.inventorySheetData = inventorySheetData;
		this.breakdownLines = breakdownLines;
	}

	public Breakdown(double coins, double check, InventorySheetData inventorySheetData) {
		super();
		this.coins = coins;
		this.check = check;
		this.inventorySheetData = inventorySheetData;
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

	public InventorySheetData getInventorySheet() {
		return inventorySheetData;
	}

	public void setInventorySheet(InventorySheetData inventorySheetData) {
		this.inventorySheetData = inventorySheetData;
	}

	public Set<BreakdownLine> getBreakdownLines() {
		return breakdownLines;
	}

	public void setBreakdownLines(Set<BreakdownLine> breakdownLines) {
		this.breakdownLines = breakdownLines;
	}

}
