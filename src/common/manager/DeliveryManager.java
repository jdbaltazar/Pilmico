package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.delivery.Delivery;

public interface DeliveryManager {

	public void addDelivery(Delivery delivery) throws Exception;

	public Delivery getDelivery(int id) throws Exception;

	public List<Delivery> getAllDeliveries() throws Exception;

	public List<Delivery> getValidDeliveries() throws Exception;

	public List<Delivery> getInvalidDeliveries() throws Exception;

	public List<Delivery> getPendingDeliveries() throws Exception;

	public List<Delivery> getAccountedDeliveries() throws Exception;

	public List<Delivery> getAllDeliveriesOn(Date date) throws Exception;

	public List<Delivery> getValidDeliveriesOn(Date date) throws Exception;

	public List<Delivery> getInvalidDeliveriesOn(Date date) throws Exception;

	public List<Delivery> getPendingDeliveriesOn(Date date) throws Exception;

	// excludes the start and end dates
	public List<Delivery> getPendingDeliveriesBetween(Date startDate, Date endDate) throws Exception;

	// excludes the start and end dates
	public List<Delivery> getPendingDeliveriesBefore(Date date) throws Exception;

	public void updateDelivery(Delivery delivery) throws Exception;

	public void deleteDelivery(Delivery delivery) throws Exception;

	public List<Date> getDatesOfPendingDeliveries() throws Exception;
}
