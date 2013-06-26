package core.persist;

import java.util.List;

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

	@Override
	public List<Sales> getSaless() throws Exception {
		return getAll(Sales.class);
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
