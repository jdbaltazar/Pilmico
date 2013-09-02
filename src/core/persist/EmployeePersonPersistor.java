package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.profile.Designation;
import common.entity.profile.Employee;
import common.entity.profile.EmploymentStatus;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = criteria.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployeesExceptManagers() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = criteria.add(Restrictions.ne("designation.id", new Integer(1))).createAlias("person", "p").addOrder(Order.desc("p.firstName"))
					.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployedEmployees() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = criteria.add(Restrictions.eq("status.id", new Integer("1"))).createAlias("person", "p").addOrder(Order.desc("p.firstName"))
					.list();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getTerminatedEmployees() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = criteria.add(Restrictions.ne("status.id", new Integer("2"))).createAlias("person", "p").addOrder(Order.desc("p.firstName"))
					.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	@Override
	public List<Employee> getEmployedEmployeesWithAccounts() throws Exception {
		List<Employee> allEmployed = getEmployedEmployees();
		List<Employee> withAccounts = new ArrayList<Employee>();
		for (Employee e : allEmployed) {
			if (e.getAccount() != null)
				withAccounts.add(e);
		}
		return withAccounts;

		// Session session = HibernateUtil.startSession();
		// Criteria criteria = session.createCriteria(Employee.class);
		// List<Employee> employees = new ArrayList<Employee>();
		// try {
		// employees = criteria.add(Restrictions.eq("status.id", new
		// Integer("1"))).add(Restrictions.isNotNull("account")).createAlias("person",
		// "p")
		// .addOrder(Order.desc("p.firstName")).list();
		// } catch (HibernateException ex) {
		// ex.printStackTrace();
		// } finally {
		// session.close();
		// }
		// return employees;
	}

	@Override
	public List<Employee> getEmployedEmployeesWithoutAccounts() throws Exception {

		List<Employee> allEmployed = getEmployedEmployees();
		List<Employee> withAccounts = new ArrayList<Employee>();
		for (Employee e : allEmployed) {
			if (e.getAccount() == null)
				withAccounts.add(e);
		}
		return withAccounts;
		// Session session = HibernateUtil.startSession();
		// Criteria criteria = session.createCriteria(Employee.class);
		// List<Employee> employees = new ArrayList<Employee>();
		// try {
		// employees = criteria.add(Restrictions.eq("status.id", new
		// Integer("1"))).add(Restrictions.isNull("account")).createAlias("person",
		// "p")
		// .addOrder(Order.desc("p.firstName")).list();
		// } catch (HibernateException ex) {
		// ex.printStackTrace();
		// } finally {
		// session.close();
		// }
		// return employees;
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Employee> getEmployedEmployees(Designation designation) throws
	// Exception {
	// Session session = HibernateUtil.startSession();
	// Criteria criteria = session.createCriteria(Employee.class);
	// List<Employee> employees = new ArrayList<Employee>();
	// try {
	// employees = criteria.add(Restrictions.eq("status.id", new
	// Integer("1"))).add(Restrictions.eq("designation.id", new Integer(2)))
	// .createAlias("person", "p").addOrder(Order.desc("p.firstName")).list();
	// } catch (HibernateException ex) {
	// ex.printStackTrace();
	// } finally {
	// session.close();
	// }
	// return employees;
	// }

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Employee> getEmployedEmployeesExcept(Designation designation)
	// throws Exception {
	// Session session = HibernateUtil.startSession();
	// Criteria criteria = session.createCriteria(Employee.class);
	// List<Employee> employees = new ArrayList<Employee>();
	// try {
	// employees = criteria.add(Restrictions.eq("status.id", new
	// Integer("1"))).add(Restrictions.ne("designation.id", new
	// Integer(2))).createAlias("person",
	// "p").addOrder(Order.desc("p.firstName"))
	// .list();
	// } catch (HibernateException ex) {
	// ex.printStackTrace();
	// } finally {
	// session.close();
	// }
	// return employees;
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployedEmployeesExceptManagers() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = criteria.add(Restrictions.eq("status.id", new Integer("1"))).add(Restrictions.ne("designation.id", new Integer(1)))
					.createAlias("person", "p").addOrder(Order.desc("p.firstName")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
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

	// persons and customers

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
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@Override
	public boolean personExists(String firstName, String lastName) throws Exception {
		List<Person> persons = getPersons();
		for (Person p : persons) {
			if (p.getFirstPlusLastName().equals(firstName + " " + lastName))
				return true;
		}
		return false;
	}

	@Override
	public void addEmploymentStatus(String empStatus) throws Exception {
		add(empStatus);
	}

	@Override
	public EmploymentStatus getEmploymentStatus(int id) throws Exception {
		return (EmploymentStatus) get(EmploymentStatus.class, id);
	}

	@Override
	public List<EmploymentStatus> getEmploymentStatuses() throws Exception {
		return getAll(EmploymentStatus.class);
	}

	@Override
	public void updateEmploymentStatus(EmploymentStatus empStatus) throws Exception {
		update(empStatus);
	}

	@Override
	public void deleteEmploymentStatus(EmploymentStatus empStatus) throws Exception {
		remove(empStatus);
	}

	@Override
	public void addDesignation(Designation designation) throws Exception {
		add(designation);
	}

	@Override
	public Designation getDesignation(int id) throws Exception {
		return (Designation) get(Designation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> getDesignations() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Designation.class);
		List<Designation> designations = new ArrayList<Designation>();
		try {
			designations = criteria.add(Restrictions.ne("id", new Integer(1))).addOrder(Order.asc("name")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return designations;
	}

	@Override
	public List<Designation> getAllDesignations() throws Exception {
		return getAll(Designation.class);
	}

	@Override
	public void updateDesignation(Designation designation) throws Exception {
		update(designation);
	}

	@Override
	public void deleteDesignation(Designation designation) throws Exception {
		remove(designation);
	}

}
