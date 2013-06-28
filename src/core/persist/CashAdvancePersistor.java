package core.persist;

import java.util.List;

import common.entity.salary.CashAdvance;
import common.manager.CashAdvanceManager;

public class CashAdvancePersistor extends Persistor implements CashAdvanceManager {

	@Override
	public void addCashAdvance(CashAdvance cashAdvance) throws Exception {
		add(cashAdvance);
	}

	@Override
	public CashAdvance getCashAdvance(int id) throws Exception {
		return (CashAdvance) get(CashAdvance.class, id);
	}

	@Override
	public List<CashAdvance> getCashAdvances() throws Exception {
		return getAll(CashAdvance.class);
	}

	@Override
	public void updateCashAdvance(CashAdvance cashAdvance) throws Exception {
		update(cashAdvance);
	}

	@Override
	public void deleteCashAdvance(CashAdvance cashAdvance) throws Exception {
		remove(cashAdvance);
	}

}
