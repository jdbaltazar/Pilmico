package common.entity.profile;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Employment")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "person_id")
	private Person person;

	@Column(name = "designation")
	private String designation;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "status_id")
	private EmploymentStatus status;

	@Column(name = "termination_date")
	@Temporal(TemporalType.DATE)
	private Date startingDate;

	@Column
	private double salary;

	@Column
	private String remarks;

	@Column(name = "ending_date")
	@Temporal(TemporalType.DATE)
	private Date terminationDate;

	public Employee() {
		super();
	}

	public Employee(Person person, String designation, EmploymentStatus status, Date startingDate, double salary, String remarks, Date terminationDate) {
		super();
		this.person = person;
		this.designation = designation;
		this.status = status;
		this.startingDate = startingDate;
		this.salary = salary;
		this.remarks = remarks;
		this.terminationDate = terminationDate;
	}

	public Employee(Person person, String designation, EmploymentStatus status) {
		super();
		this.person = person;
		this.designation = designation;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getFirstName() {
		return person.getFirstName();
	}

	public String getMiddleName() {
		return person.getMiddleName();
	}

	public String getLastName() {
		return person.getLastName();
	}

	public String getAddress() {
		return person.getAddress();
	}

	public String getContactNo() {
		return person.getContactNo();
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public EmploymentStatus getStatus() {
		return status;
	}

	public void setStatus(EmploymentStatus status) {
		this.status = status;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String toString() {
		return designation + person.toString();
	}

}
