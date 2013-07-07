package core.persist;

import java.util.ArrayList;
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
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			pullOuts = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
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
		List<PullOut> pullOuts = new ArrayList<PullOut>();
		try {
			pullOuts = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
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

}
