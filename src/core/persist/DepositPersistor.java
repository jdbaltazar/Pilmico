package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.entity.deposit.Deposit;
import common.manager.DepositManager;

public class DepositPersistor extends Persistor implements DepositManager {

	@Override
	public void addDeposit(Deposit deposit) throws Exception {
		add(deposit);
	}

	@Override
	public Deposit getDeposit(int id) throws Exception {
		return (Deposit) get(Deposit.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deposit> getAllDeposits() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Deposit.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Deposit> deposits = new ArrayList<Deposit>();
		try {
			deposits = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deposit> getValidDeposits() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Deposit.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Deposit> deposits = new ArrayList<Deposit>();
		try {
			deposits = criteria.add(Restrictions.eq("valid", true)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deposit> getInvalidDeposits() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Deposit.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Deposit> deposits = new ArrayList<Deposit>();
		try {
			deposits = criteria.add(Restrictions.eq("valid", false)).addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return deposits;
	}

	@Override
	public void updateDeposit(Deposit deposit) throws Exception {
		update(deposit);
	}

	@Override
	public void deleteDeposit(Deposit deposit) throws Exception {
		remove(deposit);
	}

	@Override
	public void addBank(Bank bank) throws Exception {
		add(bank);
	}

	@Override
	public Bank getBank(int id) throws Exception {
		return (Bank) get(Bank.class, id);
	}

	@Override
	public List<Bank> getBanks() throws Exception {
		return getAll(Bank.class);
	}

	@Override
	public void updateBank(Bank bank) throws Exception {
		update(bank);
	}

	@Override
	public void deleteBank(Bank bank) throws Exception {
		remove(bank);
	}

	@Override
	public void addBankAccount(BankAccount bankAccount) throws Exception {
		add(bankAccount);
	}

	@Override
	public BankAccount getBankAccount(int id) throws Exception {
		return (BankAccount) get(BankAccount.class, id);
	}

	@Override
	public List<BankAccount> getBankAccounts() throws Exception {
		return getAll(BankAccount.class);
	}

	@Override
	public void updateBankAccount(BankAccount bankAccount) throws Exception {
		update(bankAccount);
	}

	@Override
	public void deleteBankAccount(BankAccount bankAccount) throws Exception {
		remove(bankAccount);
	}

}
