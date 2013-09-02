package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.product.Product;
import common.entity.supplier.Supplier;
import common.manager.SupplierManager;

public class SupplierPersistor extends Persistor implements SupplierManager {

	@Override
	public void addSupplier(Supplier supplier) throws Exception {
		add(supplier);
	}

	// @Override
	// public Supplier getSupplier(String name) throws Exception {
	// return (Supplier)get(Supplier.class, name);
	// }

	@Override
	public Supplier getSupplier(int id) throws Exception {
		return (Supplier) get(Supplier.class, id);
	}

	@Override
	public List<Supplier> getSuppliers() throws Exception {
		return getAll(Supplier.class);
	}

	@Override
	public void updateSupplier(Supplier supplier) throws Exception {
		update(supplier);
	}

	@Override
	public void deleteSupplier(Supplier supplier) throws Exception {
		remove(supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Supplier searchExactSupplier(String keyword) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Product.class);
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			suppliers = criteria.add(Restrictions.eq("name", keyword)).addOrder(Order.asc("name")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return suppliers.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supplierExists(String name) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Supplier.class);
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			suppliers = criteria.add(Restrictions.like("name", name, MatchMode.EXACT)).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		if (suppliers.size() > 0)
			return true;
		return false;
	}

}
