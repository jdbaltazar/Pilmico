package common.entity.inventorysheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Denomination {

	public static final double ONE_THOUSAND = 1000d;
	public static final double FIVE_HUNDRED = 500d;
	public static final double TWO_HUNDRED = 200d;
	public static final double ONE_HUNDRED = 100d;
	public static final double FIFTY = 50d;
	public static final double TWENTY = 20d;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private double denomination;

	public Denomination() {
		super();
	}

	public Denomination(double denomination) {
		super();
		this.denomination = denomination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDenomination() {
		return denomination;
	}

	public void setDenomination(double denomination) {
		this.denomination = denomination;
	}

	public String toString() {
		return denomination + "";
	}
}
