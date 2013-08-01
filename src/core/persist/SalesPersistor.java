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
	public List<Sales> getAllSales() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getValidSales() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			sales = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getInvalidSales() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			sales = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getPendingSales() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			sales = criteria.add(Restrictions.eq("valid", true)).add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getAllSalesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			sales = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getValidSalesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			sales = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getInvalidSalesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			sales = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", false))
					.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getPendingSalesOn(Date date) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Sales.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sales> sales = new ArrayList<Sales>();
		try {
			Date lowerBound = DateTool.getDateWithoutTime(date);
			Date upperBound = DateTool.getTomorrowDate(lowerBound);
			sales = criteria.add(Restrictions.ge("date", lowerBound)).add(Restrictions.lt("date", upperBound)).add(Restrictions.eq("valid", true))
					.add(Restrictions.isNull("inventorySheetData")).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return sales;
	}

}
