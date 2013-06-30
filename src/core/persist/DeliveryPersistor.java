package core.persist;

import java.util.List;

import common.entity.delivery.Delivery;
import common.manager.DeliveryManager;

public class DeliveryPersistor extends Persistor implements DeliveryManager {

	@Override
	public void addDelivery(Delivery delivery) throws Exception {
		add(delivery);
	}

	@Override
	public Delivery getDelivery(int id) throws Exception {
		return (Delivery) get(Delivery.class, id);
	}

	@Override
	public List<Delivery> getDeliveries() throws Exception {
		return getAll(Delivery.class);
	}

	@Override
	public void updateDelivery(Delivery delivery) throws Exception {
		update(delivery);
	}

	@Override
	public void deleteDelivery(Delivery delivery) throws Exception {
		remove(delivery);
	}

}
