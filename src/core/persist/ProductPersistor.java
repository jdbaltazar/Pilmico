package core.persist;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.product.Category;
import common.entity.product.Product;
import common.manager.ProductManager;

public class ProductPersistor extends Persistor implements ProductManager {

	@Override
	public void addCategory(String name) throws Exception {
		add(new Category(name));
	}

	@Override
	public void addCategory(String name, String description) throws Exception {
		add(new Category(name, description));
	}

	@Override
	public void addCategory(Category category) throws Exception {
		add(category);
	}

	// @Override
	// public List<Category> getCategories() throws Exception {
	// return getAll(Category.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Category.class);
		List<Category> categories = new ArrayList<Category>();
		try {
			categories = criteria.addOrder(Order.asc("name")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return categories;
	}

	@Override
	public void updateCategory(Category category) throws Exception {
		update(category);
	}

	@Override
	public Category getCategory(int id) throws Exception {
		return (Category) get(Category.class, id);
	}

	@Override
	public void addProduct(Product product) throws Exception {
		add(product);
	}

	@Override
	public Product getProduct(int id) throws Exception {
		return (Product) get(Product.class, id);
	}

	@Override
	public List<Product> getProducts() throws Exception {
		return getAll(Product.class);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		update(product);
	}

	@Override
	public void deleteProduct(Product product) throws Exception {
		remove(product);
	}

	@Override
	public void deleteProduct(int id) throws Exception {
		remove(getProduct(id));
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Product> searchProducts(String name) throws Exception {
	// Session session = HibernateUtil.startSession();
	// Criteria criteria = session.createCriteria(Product.class);
	// List<Product> products = new ArrayList<Product>();
	// try {
	// products = criteria.add(Restrictions.like("name", name,
	// MatchMode.ANYWHERE)).addOrder(Order.asc("name")).list();
	// } catch (HibernateException ex) {
	// ex.printStackTrace();
	// } finally {
	// session.close();
	// }
	// return products;
	// }

	@Override
	public double computeTotalCostOfProducts() throws Exception {
		double total = 0;
		//
		// List<Product> products = getProducts();
		// for (Product i : products)
		// total += (i.getUnitSellingPrice() * i.getUnitsOnStock());
		return total;
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Product> searchProductColumns(String keyword) throws Exception
	// {
	// Session session = HibernateUtil.startSession();
	// Criteria criteria = session.createCriteria(Product.class);
	// List<Product> products = new ArrayList<Product>();
	// try {
	// Disjunction orExpression = Restrictions.disjunction();
	// orExpression.add(Restrictions.eq("id", keyword));
	// orExpression.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
	// orExpression.add(Restrictions.like("unit", keyword, MatchMode.START));
	// orExpression.add(Restrictions.like("category", keyword, MatchMode.START));
	// orExpression.add(Restrictions.like("unitSellingPrice", keyword,
	// MatchMode.START));
	// orExpression.add(Restrictions.like("unitPurchasePrice", keyword,
	// MatchMode.START));
	// orExpression.add(Restrictions.eq("quantity", keyword));
	// criteria.add(orExpression);
	// criteria.addOrder(Order.desc("id"));
	// products = criteria.list();
	// } catch (HibernateException ex) {
	// ex.printStackTrace();
	// } finally {
	// session.close();
	// }
	// return products;
	// }

}