package common.manager;

import java.util.List;

import common.entity.sales.Sales;

public interface SalesManager {

	public void addSales(Sales sales) throws Exception;

	public Sales getSales(int id) throws Exception;

	public List<Sales> getSaless() throws Exception;

	public void updateSales(Sales sales) throws Exception;

	public void deleteSales(Sales sales) throws Exception;

}