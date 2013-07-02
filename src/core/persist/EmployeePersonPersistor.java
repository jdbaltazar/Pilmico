package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.profile.Employee;
import common.entity.profile.Person;
import common.manager.EmployeePersonManager;

public class EmployeePersonPersistor extends Persistor implements EmployeePersonManager {

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

	@Override
	public void addPerson(Person person) throws Exception {
		add(person);
	}

	@Override
	public Person getPerson(int id) throws Exception {
		return (Person) get(Person.class, id);
	}

	@Override
	public List<Person> getPersons() throws Exception {
		return getAll(Person.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getCustomersOnly() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Person.class);
		List<Person> customers = new ArrayList<Person>();
		try {
			customers = criteria.add(Restrictions.eq("customer", Boolean.TRUE)).addOrder(Order.asc("firstName")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return customers;
	}

	@Override
	public void updatePerson(Person person) throws Exception {
		update(person);
	}

	@Override
	public void deletePerson(Person person) throws Exception {
		remove(person);
	}

	@Override
	public void deletePerson(int id) throws Exception {
		remove(getPerson(id));
	}

}
