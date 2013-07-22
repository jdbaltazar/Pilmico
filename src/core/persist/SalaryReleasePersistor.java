package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.salary.SalaryRelease;
import common.manager.SalaryReleaseManager;

public class SalaryReleasePersistor extends Persistor implements SalaryReleaseManager {

	@Override
	public void addSalaryRelease(SalaryRelease salaryRelease) throws Exception {
		add(salaryRelease);
	}

	@Override
	public SalaryRelease getSalaryRelease(int id) throws Exception {
		return (SalaryRelease) get(SalaryRelease.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getAllSalaryReleases() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			salaryReleases = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getValidSalaryReleases() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			salaryReleases = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getInvalidSalaryReleases() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			salaryReleases = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getPendingSalaryReleases() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			salaryReleases = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date"))
					.list();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@Override
	public void updateSalaryRelease(SalaryRelease salaryRelease) throws Exception {
		update(salaryRelease);
	}

	@Override
	public void deleteSalaryRelease(SalaryRelease salaryRelease) throws Exception {
		remove(salaryRelease);
	}

}
