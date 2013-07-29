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
public class BreakdownLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "breakdown_id")
	private Breakdown breakdown;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "denomination_id")
	private Denomination denomination;

	@Column
	private double quantity;

	public BreakdownLine() {
		super();
	}

	public BreakdownLine(Breakdown breakdown, Denomination denomination, double quantity) {
		super();
		this.breakdown = breakdown;
		this.denomination = denomination;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Breakdown getBreakdown() {
		return breakdown;
	}

	public void setBreakdown(Breakdown breakdown) {
		this.breakdown = breakdown;
	}

	public Denomination getDenomination() {
		return denomination;
	}

	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return quantity * denomination.getDenomination();
	}

}
