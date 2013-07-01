package common.manager;

import java.util.List;

import common.entity.product.Category;
import common.entity.product.Product;

public interface ProductManager {

	public void addProduct(Product product) throws Exception;

	public Product getProduct(int id) throws Exception;

	public List<Product> getProducts() throws Exception;

	public void updateProduct(Product product) throws Exception;

	public void deleteProduct(Product product) throws Exception;

	public void deleteProduct(int id) throws Exception;

	// public List<Product> searchProducts(String name) throws Exception;
	//
	// public List<Product> searchProductColumns(String keyword) throws
	// Exception;

	public double computeTotalCostOfProducts() throws Exception;

	// category

	void addCategory(String name) throws Exception;

	void addCategory(String name, String description) throws Exception;

	void addCategory(Category category) throws Exception;

	List<Category> getCategories() throws Exception;

	void updateCategory(Category category) throws Exception;

	Category getCategory(int id) throws Exception;

}
