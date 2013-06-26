package common.manager;

import java.util.List;

import common.entity.profile.Employee;

public interface EmployeeManager {

	public void addEmployee(Employee employee) throws Exception;

	public Employee getEmployee(int id) throws Exception;

	public List<Employee> getEmployees() throws Exception;

	public void updateEmployee(Employee employee) throws Exception;

	public void deleteEmployee(Employee employee) throws Exception;

	public void deleteEmployee(int id) throws Exception;

}
