package common.entity.salary;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CADeduction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cash_advance_id")
	private CashAdvance cashAdvance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "salary_release_id")
	private SalaryRelease salaryRelease;

	public CADeduction(CashAdvance cashAdvance, SalaryRelease salaryRelease) {
		super();
		this.cashAdvance = cashAdvance;
		this.salaryRelease = salaryRelease;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CashAdvance getCashAdvance() {
		return cashAdvance;
	}

	public void setCashAdvance(CashAdvance cashAdvance) {
		this.cashAdvance = cashAdvance;
	}

	public SalaryRelease getSalaryRelease() {
		return salaryRelease;
	}

	public void setSalaryRelease(SalaryRelease salaryRelease) {
		this.salaryRelease = salaryRelease;
	}

}
