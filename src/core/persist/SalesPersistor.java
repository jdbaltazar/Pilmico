package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.sales.Sales;
import common.manager.SalesManager;

public class SalesPersistor extends Persistor implements SalesManager {

	@Override
	public void addSales(Sales sales) throws Exception {
		add(sales);
	}

	@Override
	public Sales getSales(int id) throws Exception {
		return (Sales) get(Sales.class, id);
	}

	// @Override
	// public List<Sales> getSales() throws Exception {
	// return getAll(Sales.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getSales() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			sales = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

	@Override
	public void updateSales(Sales sales) throws Exception {
		update(sales);
	}

	@Override
	public void deleteSales(Sales sales) throws Exception {
		remove(sales);
	}

}
