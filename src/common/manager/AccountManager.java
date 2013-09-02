package common.manager;

import java.util.List;

import common.entity.profile.Account;
import common.entity.profile.AccountType;

public interface AccountManager {

	// account types

	public void addAccountType(String accType) throws Exception;

	public AccountType getAccountType(int id) throws Exception;

	public List<AccountType> getAccountTypes() throws Exception;

	public void updateAccountType(AccountType accType) throws Exception;

	public void deleteAccountType(AccountType accType) throws Exception;

	// account

	public void addAccount(Account acc) throws Exception;

	public Account getAccount(String username) throws Exception;

	public Account getAccount(int id) throws Exception;

	public List<Account> getActiveAccounts() throws Exception;

	public List<Account> getAccounts() throws Exception;

	public List<Account> getAccountThenAddToList(int id) throws Exception;

	public List<Account> getAccountsExcludeManagers() throws Exception;

	public void updateAccount(Account acc) throws Exception;

	public void deleteAccount(Account acc) throws Exception;

	public void deleteAccount(int id) throws Exception;

	public List<Account> searchAccounts(String username) throws Exception;

	public boolean usernameTaken(String username) throws Exception;

}
