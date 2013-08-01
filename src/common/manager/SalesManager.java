package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.sales.Sales;

public interface SalesManager {

	public void addSales(Sales sales) throws Exception;

	public Sales getSales(int id) throws Exception;

	public List<Sales> getAllSales() throws Exception;

	public List<Sales> getValidSales() throws Exception;

	public List<Sales> getInvalidSales() throws Exception;

	public List<Sales> getPendingSales() throws Exception;

	public void updateSales(Sales sales) throws Exception;

	public void deleteSales(Sales sales) throws Exception;
	
	public List<Sales> getAllSalesOn(Date date) throws Exception;

	public List<Sales> getValidSalesOn(Date date) throws Exception;

	public List<Sales> getInvalidSalesOn(Date date) throws Exception;

	public List<Sales> getPendingSalesOn(Date date) throws Exception;

}