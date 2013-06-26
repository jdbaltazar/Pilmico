package core.persist;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class Persistor {

	public Persistor() {
		super();
	}

	protected void add(Object entity) throws Exception {
		Session session = HibernateUtil.startSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (HibernateException ex) {
			tx.rollback();
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			session.close();
		}
	}

	protected void update(Object entity) throws Exception {
		Session session = HibernateUtil.startSession();
		Transaction tx = session.beginTransaction();
		try {
			session.merge(entity);
			tx.commit();
		} catch (HibernateException ex) {
			tx.rollback();
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			session.close();
		}
	}

	protected void remove(Object entity) throws Exception {
		Session session = HibernateUtil.startSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(entity);
			tx.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
			throw new Exception(ex.getMessage());
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> getAll(Class<T> c) throws Exception {
		Session session = HibernateUtil.startSession();
		Transaction tx = session.beginTransaction();
		try {
			List<T> list = session.createQuery("from " + c.getSimpleName()).list();
			tx.commit();
			return list;
		} catch (HibernateException ex) {
			tx.rollback();
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			session.close();
		}
	}

	protected Object get(Class<?> c, Serializable id) throws Exception {
		Session session = HibernateUtil.startSession();
		Transaction tx = session.beginTransaction();
		try {
			Object result = session.get(c, id);
			tx.commit();
			return result;
		} catch (HibernateException ex) {
			tx.rollback();
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			session.close();
		}
	}

}