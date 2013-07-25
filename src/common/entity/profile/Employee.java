package common.entity.profile;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Employment")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "designation_id")
	private Designation designation;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "status_id")
	private EmploymentStatus status;

	@Column(name = "starting_date")
	@Temporal(TemporalType.DATE)
	private Date startingDate;

	@Column
	private double salary;

	@Column
	private String remarks;

	@Column(name = "termination_date")
	@Temporal(TemporalType.DATE)
	private Date terminationDate;

	@OneToOne(mappedBy = "employee", fetch = FetchType.EAGER)
	private Account account;

	public Employee() {
		super();
	}

	public Employee(Person person, Designation designation, EmploymentStatus status, Date startingDate, double salary, String remarks,
			Date terminationDate) {
		super();
		this.person = person;
		this.designation = designation;
		this.status = status;
		this.startingDate = startingDate;
		this.salary = salary;
		this.remarks = remarks;
		this.terminationDate = terminationDate;
	}

	public Employee(Person person, Designation designation, EmploymentStatus status) {
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

	public void setFirstName(String firstName) {
		person.setFirstName(firstName);
	}

	public void setMiddleName(String middleName) {
		person.setMiddleName(middleName);
	}

	public void setLastName(String lastName) {
		person.setLastName(lastName);
	}

	public void setAddress(String address) {
		person.setAddress(address);
	}

	public void setContactNo(String contactNo) {
		person.setContactNo(contactNo);
	}

	public void setCustomer(boolean customer) {
		person.setCustomer(customer);
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

	public String getFirstPlusLastName() {
		return person.getFirstName() + " " + person.getLastName();
	}

	public String getAddress() {
		return person.getAddress();
	}

	public String getContactNo() {
		return person.getContactNo();
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	// public EmploymentStatus getStatus() {
	// return status;
	// }
	//
	// public void setStatus(EmploymentStatus status) {
	// this.status = status;
	// }

	public Date getStartingDate() {
		return startingDate;
	}

	public EmploymentStatus getStatus() {
		return status;
	}

	public void setStatus(EmploymentStatus status) {
		this.status = status;
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

	public Account getAccount() {
		return account;
	}

	public String toString() {
		return designation + " " + person.toString();
	}

}
