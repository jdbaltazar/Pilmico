package core.persist;

import java.util.ArrayList;
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

	@Override
	public void updateDiscountIssue(DiscountIssue discountIssue) throws Exception {
		update(discountIssue);
	}

	@Override
	public void deleteDiscountIssue(DiscountIssue discountIssue) throws Exception {
		remove(discountIssue);
	}

}
