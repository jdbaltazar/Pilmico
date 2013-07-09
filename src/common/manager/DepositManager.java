package common.manager;

import java.util.List;

import common.entity.deposit.Deposit;

public interface DepositManager {

	public void addDeposit(Deposit deposit) throws Exception;

	public Deposit getDeposit(int id) throws Exception;

	public List<Deposit> getAllDeposits() throws Exception;

	public List<Deposit> getValidDeposits() throws Exception;

	public List<Deposit> getInvalidDeposits() throws Exception;

	public void updateDeposit(Deposit deposit) throws Exception;

	public void deleteDeposit(Deposit deposit) throws Exception;

}
