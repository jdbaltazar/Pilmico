package common.manager;

import java.util.List;

import common.entity.profile.EmploymentStatus;
import common.entity.profile.Employee;
import common.entity.profile.Person;

public interface EmployeePersonManager {

	// employee types

	public void addEmploymentStatus(String empStatus) throws Exception;

	public EmploymentStatus getEmploymentStatus(int id) throws Exception;

	public List<EmploymentStatus> getEmploymentStatuses() throws Exception;

	public void updateEmploymentStatus(EmploymentStatus empStatus) throws Exception;

	public void deleteEmploymentStatus(EmploymentStatus empStatus) throws Exception;

	// employees

	public void addEmployee(Employee employee) throws Exception;

	public Employee getEmployee(int id) throws Exception;

	public List<Employee> getEmployees() throws Exception;

	public List<Employee> getEmployeesWithoutAccounts() throws Exception;

	public void updateEmployee(Employee employee) throws Exception;

	public void deleteEmployee(Employee employee) throws Exception;

	public void deleteEmployee(int id) throws Exception;

	// persons

	public void addPerson(Person person) throws Exception;

	public Person getPerson(int id) throws Exception;

	public List<Person> getPersons() throws Exception;

	public List<Person> getCustomersOnly() throws Exception;

	public void updatePerson(Person person) throws Exception;

	public void deletePerson(Person person) throws Exception;

	public void deletePerson(int id) throws Exception;

}
