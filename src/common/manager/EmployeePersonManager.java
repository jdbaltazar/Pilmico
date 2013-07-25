package common.manager;

import java.util.List;

import common.entity.profile.Designation;
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

	public List<Employee> getAllEmployees() throws Exception;

	public List<Employee> getEmployedEmployees() throws Exception;

	public List<Employee> getTerminatedEmployees() throws Exception;

	public List<Employee> getEmployedEmployeesWithAccounts() throws Exception;

	public List<Employee> getEmployedEmployeesWithoutAccounts() throws Exception;

	public List<Employee> getEmployedEmployees(Designation designation) throws Exception;

	public List<Employee> getEmployedEmployeesExcept(Designation designation) throws Exception;

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

	// designations

	public void addDesignation(Designation designation) throws Exception;

	public Designation getDesignation(int id) throws Exception;

	public List<Designation> getAllDesignations() throws Exception;

	public List<Designation> getDesignations() throws Exception;

	public void updateDesignation(Designation designation) throws Exception;

	public void deleteDesignation(Designation designation) throws Exception;

}
