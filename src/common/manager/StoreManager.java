package common.manager;

import common.entity.store.Store;

public interface StoreManager {

	public void updateStore(Store store) throws Exception;

	public Store getStore() throws Exception;

}
