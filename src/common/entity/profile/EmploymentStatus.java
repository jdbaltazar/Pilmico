package common.entity.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "EmploymentStatus")
public class EmploymentStatus {

	public static final String EMPLOYED = "Employed";
	public static final String TERMINATED = "Terminated";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "status")
	public String status;

	public EmploymentStatus() {
		super();
	}

	public EmploymentStatus(String status) {
		super();
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return status;
	}

}
