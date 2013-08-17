package common.entity.profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.product.Price;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_no")
	private String contactNo;

	@Column
	private boolean customer;

	@OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employments = new HashSet<Employee>();

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AccountReceivable> accountReceivables = new HashSet<AccountReceivable>();

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, boolean customer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.customer = customer;
	}

	public Person(String firstName, String middleName, String lastName, String address, String contactNo, boolean customer) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo = contactNo;
		this.customer = customer;
	}

	public Person(Person p) {
		super();
		this.firstName = p.getFirstName();
		this.middleName = p.getMiddleName();
		this.lastName = p.getLastName();
		this.address = p.getAddress();
		this.contactNo = p.getContactNo();
		this.customer = p.isCustomer();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstPlusLastName() {
		return firstName + " " + lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {

		if (contactNo == null)
			return "";

		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isCustomer() {
		return customer;
	}

	public void setCustomer(boolean customer) {
		this.customer = customer;
	}

	// public Set<Employee> getEmployments() {
	// return employments;
	// }

	public List<Employee> getEmploymentHistory() {
		return orderEmploymentHistory(copyEmployments(employments));
	}

	private List<Employee> copyEmployments(Set<Employee> orig) {
		List<Employee> copy = new ArrayList<Employee>();
		for (Employee emp : orig) {
			Person person = new Person(emp.getPerson());
			emp.setId(emp.getPerson().getId());
			Designation designation = new Designation(emp.getDesignation().getName());
			designation.setId(emp.getDesignation().getId());
			EmploymentStatus employmentStatus = new EmploymentStatus(emp.getStatus().getStatus());
			employmentStatus.setId(emp.getStatus().getId());
			Employee emp2 = new Employee(person, designation, employmentStatus, emp.getStartingDate(), emp.getSalary(), emp.getRemarks(),
					emp.getTerminationDate());
			emp2.setId(emp.getId());
			copy.add(emp2);
		}
		return copy;
	}

	private List<Employee> orderEmploymentHistory(List<Employee> employments) {
		List<Employee> ordered = new ArrayList<Employee>();
		int origSize = employments.size();
		for (int i = 0; i < origSize; i++) {
			Employee temp = getMostRecentEmploymentIn(employments);
			ordered.add(temp);
			employments.remove(employments.indexOf(temp));
		}
		return ordered;
	}

	private Employee getMostRecentEmploymentIn(List<Employee> employments) {
		Employee mostRecent = null;
		for (Employee e : employments) {
			if (mostRecent == null)
				mostRecent = e;
			else {
				if (e.getStartingDate().after(mostRecent.getStartingDate()))
					mostRecent = e;
			}
		}
		return mostRecent;
	}

	public Set<AccountReceivable> getAccountReceivables() {
		return accountReceivables;
	}

	public void setAccountReceivables(Set<AccountReceivable> accountReceivables) {
		this.accountReceivables = accountReceivables;
	}

	public double getTotalAccountReceivables() {
		double total = 0d;
		for (AccountReceivable ar : accountReceivables) {
			total += ar.getAccountReceivablesAmount();
		}
		return total;
	}

	public String toString() {
		return firstName + " " + lastName;
	}

}
