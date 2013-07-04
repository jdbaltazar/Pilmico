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
public class FeeDeduction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fee_id")
	private Fee fee;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "salary_release_id")
	private SalaryRelease salaryRelease;
	
	@Column
	private double amount;
	

	public FeeDeduction() {
		super();
	}

	public FeeDeduction(Fee fee, SalaryRelease salaryRelease, double amount) {
		super();
		this.fee = fee;
		this.salaryRelease = salaryRelease;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public SalaryRelease getSalaryRelease() {
		return salaryRelease;
	}

	public void setSalaryRelease(SalaryRelease salaryRelease) {
		this.salaryRelease = salaryRelease;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
