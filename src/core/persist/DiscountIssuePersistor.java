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

import common.entity.discountissue.DiscountIssue;
import common.manager.DiscountIssueManager;

public class DiscountIssuePersistor extends Persistor implements DiscountIssueManager {

	@Override
	public void addDiscountIssue(DiscountIssue discountIssue) throws Exception {
		add(discountIssue);
	}

	@Override
	public DiscountIssue getDiscountIssue(int id) throws Exception {
		return (DiscountIssue) get(DiscountIssue.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getAllDiscountIssues() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		List<DiscountIssue> discounts = new ArrayList<DiscountIssue>();
		try {
			discounts = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discounts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getValidDiscountIssues() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		List<DiscountIssue> discounts = new ArrayList<DiscountIssue>();
		try {
			discounts = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discounts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getInvalidDiscountIssues() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		List<DiscountIssue> discounts = new ArrayList<DiscountIssue>();
		try {
			discounts = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discounts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getPendingDiscountIssues() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		List<DiscountIssue> discounts = new ArrayList<DiscountIssue>();
		try {
			discounts = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discounts;
	}

	@Override
	public void updateDiscountIssue(DiscountIssue discountIssue) throws Exception {
		update(discountIssue);
	}

	@Override
	public void deleteDiscountIssue(DiscountIssue discountIssue) throws Exception {
		remove(discountIssue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getAllDiscountIssuesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiscountIssue> discountIssues = new ArrayList<DiscountIssue>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			discountIssues = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date"))
					.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discountIssues;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getValidDiscountIssuesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiscountIssue> discountIssues = new ArrayList<DiscountIssue>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			discountIssues = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discountIssues;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getInvalidDiscountIssuesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiscountIssue> discountIssues = new ArrayList<DiscountIssue>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			discountIssues = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discountIssues;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getPendingDiscountIssuesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiscountIssue> discountIssues = new ArrayList<DiscountIssue>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			discountIssues = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return discountIssues;
	}

	@Override
	public List<Date> getDatesOfPendingDiscountIssues() throws Exception {
		List<DiscountIssue> discountIssues = getPendingDiscountIssues();
		List<Date> dates = new ArrayList<Date>();
		for (DiscountIssue discountIssue : discountIssues) {
			Date date = DateTool.getDateWithoutTime(discountIssue.getDate());
			boolean found = false;
			for (Date d : dates) {
				if (d.compareTo(date) == 0) {
					found = true;
				}
			}
			if (!found) {
				dates.add(date);
			}
		}
		return dates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getPendingDiscountIssuesBetween(Date startDate, Date endDate) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiscountIssue> deposits = new ArrayList<DiscountIssue>();
		try {
			Date lowerBound = DateTool.getTomorrowDate(DateTool.getDateWithoutTime(startDate));
			Date upperBound = DateTool.getDateWithoutTime(endDate);
			deposits = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountIssue> getPendingDiscountIssuesBefore(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(DiscountIssue.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiscountIssue> deposits = new ArrayList<DiscountIssue>();
		try {
			deposits = criteria.add(Restrictions.lt("date", DateTool.getDateWithoutTime(date))).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

}
