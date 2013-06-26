package common.entity.supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "tin")
	private String tin;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "remarks")
	private String remarks;

	public Supplier() {
		super();
	}

	public Supplier(String name) {
		super();
		this.name = name;
	}

	public Supplier(String name, String address, String tin, String contactNo, String contactPerson, String remarks) {
		super();
		this.name = name;
		this.address = address;
		this.tin = tin;
		this.contactNo = contactNo;
		this.contactPerson = contactPerson;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String toString() {
		return name;
	}
}
