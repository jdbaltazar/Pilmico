package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;

public interface CashAdvanceManager {

	// cash advances

	public void addCashAdvance(CashAdvance cashAdvance) throws Exception;

	public CashAdvance getCashAdvance(int id) throws Exception;

	public List<CashAdvance> getAllCashAdvances() throws Exception;

	public List<CashAdvance> getValidCashAdvances() throws Exception;

	public List<CashAdvance> getInvalidCashAdvances() throws Exception;

	public List<CashAdvance> getPendingCashAdvances() throws Exception;

	public void updateCashAdvance(CashAdvance cashAdvance) throws Exception;

	public void deleteCashAdvance(CashAdvance cashAdvance) throws Exception;

	public List<CashAdvance> getAllCashAdvancesOn(Date date) throws Exception;

	public List<CashAdvance> getValidCashAdvancesOn(Date date) throws Exception;

	public List<CashAdvance> getInvalidCashAdvancesOn(Date date) throws Exception;

	public List<CashAdvance> getPendingCashAdvancesOn(Date date) throws Exception;

	public List<Date> getDatesOfPendingCashAdvances() throws Exception;

	// cash advance payments

	public void addCAPayment(CAPayment caPayment) throws Exception;

	public CAPayment getCAPayment(int id) throws Exception;

	public List<CAPayment> getAllCAPayments() throws Exception;

	public List<CAPayment> getValidCAPayments() throws Exception;

	public List<CAPayment> getInvalidCAPayments() throws Exception;

	public List<CAPayment> getPendingCAPayments() throws Exception;

	public void updateCAPayment(CAPayment caPayment) throws Exception;

	public void deleteCAPayment(CAPayment caPayment) throws Exception;

	public List<CAPayment> getAllCAPaymentsOn(Date date) throws Exception;

	public List<CAPayment> getValidCAPaymentsOn(Date date) throws Exception;

	public List<CAPayment> getInvalidCAPaymentsOn(Date date) throws Exception;

	public List<CAPayment> getPendingCAPaymentsOn(Date date) throws Exception;

	public List<Date> getDatesOfPendingCAPayments() throws Exception;
}
