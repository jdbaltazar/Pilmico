package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.manager.AccountManager;
import core.security.Blowfish;

public class AccountPersistor extends Persistor implements AccountManager {

	@Override
	public void addAccountType(String accType) throws Exception {
		add(new AccountType(accType));
	}

	@Override
	public AccountType getAccountType(int id) throws Exception {
		return (AccountType) get(AccountType.class, id);
	}

	@Override
	public List<AccountType> getAccountTypes() throws Exception {
		return getAll(AccountType.class);
	}

	@Override
	public void updateAccountType(AccountType accType) throws Exception {
		update(accType);
	}

	@Override
	public void deleteAccountType(AccountType accType) throws Exception {
		deleteAccountType(accType);
	}

	@Override
	public void addAccount(Account acc) throws Exception {
		acc.setPassword(Blowfish.encrypt(acc.getPassword()));
		add(acc);
	}

	@Override
	public Account getAccount(int id) throws Exception {
		Account acc = (Account) get(Account.class, id);
		if (acc != null)
			acc.setPassword(Blowfish.decrypt(acc.getPassword()));
		return acc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAccounts() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Account.class);
		List<Account> accounts = new ArrayList<Account>();
		try {
			accounts = criteria.add(Restrictions.ne("id", new Integer(1))).addOrder(Order.asc("username")).list();
			for (Account acc : accounts) {
				acc.setPassword(Blowfish.decrypt(acc.getPassword()));
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accounts;
	}

	@Override
	public void updateAccount(Account acc) throws Exception {
		acc.setPassword(Blowfish.encrypt(acc.getPassword()));
		update(acc);
	}

	@Override
	public void deleteAccount(Account acc) throws Exception {
		remove(acc);
	}

	// @Override
	// public void deleteAccount(String username) throws Exception {
	// remove(getAccount(username));
	// }

	@Override
	public void deleteAccount(int id) throws Exception {
		remove(getAccount(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> searchAccounts(String username) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Account.class);
		List<Account> accounts = new ArrayList<Account>();
		try {
			accounts = criteria.add(Restrictions.like("username", username, MatchMode.START)).addOrder(Order.asc("username")).list();
			for (Account acc : accounts) {
				acc.setPassword(Blowfish.decrypt(acc.getPassword()));
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return accounts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Account getAccount(String username) throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Account.class);
		List<Account> accounts = new ArrayList<Account>();
		Account account = null;
		try {
			accounts = criteria.add(Restrictions.like("username", username, MatchMode.EXACT)).list();
			for (Account acc : accounts) {
				acc.setPassword(Blowfish.decrypt(acc.getPassword()));
			}
			if (accounts.size() == 1) {
				account = accounts.get(0);
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

}
