package core.persist;

import gui.forms.util.DateTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.salary.Fee;
import common.entity.salary.FeeDeduction;
import common.entity.salary.SalaryRelease;
import common.manager.SalaryReleaseManager;

public class SalaryReleasePersistor extends Persistor implements
		SalaryReleaseManager {

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
			salaryReleases = criteria.add(Restrictions.eq("valid", true))
					.addOrder(Order.desc("date")).list();
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
			salaryReleases = criteria.add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
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
			salaryReleases = criteria.add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@Override
	public void updateSalaryRelease(SalaryRelease salaryRelease)
			throws Exception {
		update(salaryRelease);
	}

	@Override
	public void deleteSalaryRelease(SalaryRelease salaryRelease)
			throws Exception {
		remove(salaryRelease);
	}

	@Override
	public void addFees(Fee fee) throws Exception {
		add(fee);
	}

	@Override
	public Fee getFee(int id) throws Exception {
		return (Fee) get(Fee.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Fee searchFee(String name) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Fee.class);
		List<Fee> fees = new ArrayList<Fee>();
		Fee fee = null;
		try {
			fees = criteria.add(Restrictions.eq("name", name)).list();
			if (fees.size() > 0) {
				fee = fees.get(0);
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return fee;
	}

	@Override
	public List<Fee> getFees() throws Exception {
		return getAll(Fee.class);
	}

	@Override
	public void updateFees(Fee fee) throws Exception {
		update(fee);
	}

	@Override
	public void deleteFee(Fee fee) throws Exception {
		remove(fee);
	}

	@Override
	public void deleteFee(int id) throws Exception {
		remove(getFee(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getMostRecentAmountForFee(int feeId) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(FeeDeduction.class);
		List<FeeDeduction> fDeductions = new ArrayList<FeeDeduction>();
		try {
			fDeductions = criteria.add(Restrictions.eq("fee.id", feeId))
					.addOrder(Order.desc("id")).list();
			if (fDeductions.size() > 0) {
				return fDeductions.get(0).getAmount();
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return 0d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getAllSalaryReleasesOn(Date date)
			throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			salaryReleases = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getValidSalaryReleasesOn(Date date)
			throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			salaryReleases = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getInvalidSalaryReleasesOn(Date date)
			throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			salaryReleases = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryRelease> getPendingSalaryReleasesOn(Date date)
			throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(SalaryRelease.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SalaryRelease> salaryReleases = new ArrayList<SalaryRelease>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			salaryReleases = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return salaryReleases;
	}

}
