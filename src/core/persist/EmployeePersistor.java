package core.persist;

import java.util.List;

import common.entity.profile.Employee;
import common.manager.EmployeeManager;

public class EmployeePersistor extends Persistor implements EmployeeManager {

	@Override
	public void addEmployee(Employee employee) throws Exception {
		add(employee);
	}

	@Override
	public Employee getEmployee(int id) throws Exception {
		return (Employee) get(Employee.class, id);
	}

	@Override
	public List<Employee> getEmployees() throws Exception {
		return getAll(Employee.class);
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception {
		update(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) throws Exception {
		remove(employee);
	}

	@Override
	public void deleteEmployee(int id) throws Exception {
		remove(getEmployee(id));
	}

}
