package common.manager;

import java.util.List;

import common.entity.supplier.Supplier;

public interface SupplierManager {

	public void addSupplier(Supplier supplier) throws Exception;

	public Supplier getSupplier(int id) throws Exception;

	public List<Supplier> getSuppliers() throws Exception;

	public void updateSupplier(Supplier supplier) throws Exception;

	public void deleteSupplier(Supplier supplier) throws Exception;

	public Supplier searchExactSupplier(String keyword) throws Exception;

	public boolean supplierExists(String name) throws Exception;

}
