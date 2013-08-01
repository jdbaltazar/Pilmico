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

import common.entity.pullout.PullOut;
import common.manager.PullOutManager;

public class PullOutPersistor extends Persistor implements PullOutManager {

	@Override
	public void addPullOut(PullOut pullOut) throws Exception {
		add(pullOut);
	}

	@Override
	public PullOut getPullOut(int id) throws Exception {
		return (PullOut) get(PullOut.class, id);
	}

	// @Override
	// public List<PullOut> getPullOuts() throws Exception {
	// return getAll(PullOut.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getAllPullOuts() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			pullOuts = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getValidPullOuts() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			pullOuts = criteria.add(Restrictions.eq("valid", true))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getInvalidPullOuts() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			pullOuts = criteria.add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getPendingPullOuts() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			pullOuts = criteria.add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@Override
	public void updatePullOut(PullOut pullOut) throws Exception {
		update(pullOut);
	}

	@Override
	public void deletePullOut(PullOut pullOut) throws Exception {
		remove(pullOut);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getAllPullOutsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			pullOuts = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getValidPullOutsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			pullOuts = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getInvalidPullOutsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			pullOuts = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PullOut> getPendingPullOutsOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(PullOut.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			pullOuts = criteria.add(Restrictions.ge("date", lowerBound))
					.add(Restrictions.lt("date", upperBound))
					.add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData"))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return pullOuts;
	}

}
