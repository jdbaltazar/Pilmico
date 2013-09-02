package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.entity.deposit.Deposit;

public interface DepositManager {

	// deposits

	public void addDeposit(Deposit deposit) throws Exception;

	public Deposit getDeposit(int id) throws Exception;

	public List<Deposit> getAllDeposits() throws Exception;

	public List<Deposit> getValidDeposits() throws Exception;

	public List<Deposit> getInvalidDeposits() throws Exception;

	public List<Deposit> getPendingDeposits() throws Exception;

	public List<Deposit> getAllDepositsOn(Date date) throws Exception;

	public List<Deposit> getValidDepositsOn(Date date) throws Exception;

	public List<Deposit> getInvalidDepositsOn(Date date) throws Exception;

	public List<Deposit> getPendingDepositsOn(Date date) throws Exception;

	// excludes the start and end dates
	public List<Deposit> getPendingDepositsBetween(Date startDate, Date endDate) throws Exception;

	public List<Deposit> getPendingDepositsBefore(Date date) throws Exception;

	public void updateDeposit(Deposit deposit) throws Exception;

	public void deleteDeposit(Deposit deposit) throws Exception;

	public List<Date> getDatesOfPendingDeposits() throws Exception;

	// bank

	public void addBank(Bank bank) throws Exception;

	public Bank getBank(int id) throws Exception;

	public List<Bank> getBanks() throws Exception;

	public void updateBank(Bank bank) throws Exception;

	public void deleteBank(Bank bank) throws Exception;

	public boolean bankExists(String name) throws Exception;

	// bank accounts

	public void addBankAccount(BankAccount bankAccount) throws Exception;

	public BankAccount getBankAccount(int id) throws Exception;

	public List<BankAccount> getBankAccounts() throws Exception;

	public void updateBankAccount(BankAccount bankAccount) throws Exception;

	public void deleteBankAccount(BankAccount bankAccount) throws Exception;

}
