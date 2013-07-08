package common.manager;

import java.util.List;

import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;

public interface CashAdvanceManager {

	// cash advances

	public void addCashAdvance(CashAdvance cashAdvance) throws Exception;

	public CashAdvance getCashAdvance(int id) throws Exception;

	public List<CashAdvance> getCashAdvances() throws Exception;

	public void updateCashAdvance(CashAdvance cashAdvance) throws Exception;

	public void deleteCashAdvance(CashAdvance cashAdvance) throws Exception;

	// cash advance payments

	public void addCAPayment(CAPayment caPayment) throws Exception;

	public CAPayment getCAPayment(int id) throws Exception;

	public List<CAPayment> getCAPayments() throws Exception;

	public void updateCAPayment(CAPayment caPayment) throws Exception;

	public void deleteCAPayment(CAPayment caPayment) throws Exception;

}
