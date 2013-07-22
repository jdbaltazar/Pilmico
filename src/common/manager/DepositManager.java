package common.manager;

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

	public void updateDeposit(Deposit deposit) throws Exception;

	public void deleteDeposit(Deposit deposit) throws Exception;

	// bank

	public void addBank(Bank bank) throws Exception;

	public Bank getBank(int id) throws Exception;

	public List<Bank> getBanks() throws Exception;

	public void updateBank(Bank bank) throws Exception;

	public void deleteBank(Bank bank) throws Exception;

	// bank accounts

	public void addBankAccount(BankAccount bankAccount) throws Exception;

	public BankAccount getBankAccount(int id) throws Exception;

	public List<BankAccount> getBankAccounts() throws Exception;

	public void updateBankAccount(BankAccount bankAccount) throws Exception;

	public void deleteBankAccount(BankAccount bankAccount) throws Exception;

}
