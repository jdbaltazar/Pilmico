package core.persist;

import java.util.List;

import common.entity.store.Store;
import common.manager.StoreManager;

public class StorePersistor extends Persistor implements StoreManager {

	@Override
	public void updateStore(Store store) throws Exception {
		update(store);
	}

	@Override
	public Store getStore() throws Exception {
		List<Store> infos = getAll(Store.class);
		if (infos.size() > 0)
			return infos.get(0);
		return null;
	}

}
