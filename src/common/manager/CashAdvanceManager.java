package common.manager;

import java.util.List;

import common.entity.salary.CashAdvance;

public interface CashAdvanceManager {

	public void addCashAdvance(CashAdvance cashAdvance) throws Exception;

	public CashAdvance getCashAdvance(int id) throws Exception;

	public List<CashAdvance> getCashAdvances() throws Exception;

	public void updateCashAdvance(CashAdvance cashAdvance) throws Exception;

	public void deleteCashAdvance(CashAdvance cashAdvance) throws Exception;

}
