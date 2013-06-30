package common.manager;

import java.util.List;

import common.entity.delivery.Delivery;

public interface DeliveryManager {

	public void addDelivery(Delivery delivery) throws Exception;

	public Delivery getDelivery(int id) throws Exception;

	public List<Delivery> getDeliveries() throws Exception;

	public void updateDelivery(Delivery delivery) throws Exception;

	public void deleteDelivery(Delivery delivery) throws Exception;

}
