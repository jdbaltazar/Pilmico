package common.entity.profile;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_type")
	private AccountType accountType;

	public Account() {
		super();
	}

	public Account(String username, String password, AccountType accountType, Employee employee) {
		super();
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean comparePassword(char[] input) {
		boolean isCorrect = false;
		char[] correctPassword = password.toCharArray();

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Zero out the password.
		Arrays.fill(correctPassword, '0');
		return isCorrect;

	}

	public String toString() {
		return username;
	}

}
