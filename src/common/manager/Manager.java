package common.manager;

import common.entity.profile.Account;

import core.persist.AccountPersistor;
import core.persist.AccountReceivablePersistor;
import core.persist.CashAdvancePersistor;
import core.persist.DailyExpensePersistor;
import core.persist.DeliveryPersistor;
import core.persist.EmployeePersonPersistor;
import core.persist.ProductPersistor;
import core.persist.LogPersistor;
import core.persist.NotePersistor;
import core.persist.PullOutPersistor;
import core.persist.SalesPersistor;
import core.persist.StorePersistor;
import core.persist.SupplierPersistor;

public class Manager {

	public static Account loggedInAccount = null;

	public static AccountManager accountManager = new AccountPersistor();

	public static AccountReceivableManager accountReceivableManager = new AccountReceivablePersistor();

	public static CashAdvanceManager cashAdvanceManager = new CashAdvancePersistor();

	public static DailyExpensesManager dailyExpenseManager = new DailyExpensePersistor();

	public static DeliveryManager deliveryManager = new DeliveryPersistor();

	public static EmployeePersonManager employeePersonManager = new EmployeePersonPersistor();

	public static LogManager logManager = new LogPersistor();

	public static NoteManager noteManager = new NotePersistor();

	public static ProductManager productManager = new ProductPersistor();

	public static PullOutManager pullOutManager = new PullOutPersistor();

	public static SalesManager salesManager = new SalesPersistor();

	public static StoreManager storeManager = new StorePersistor();

	public static SupplierManager supplierManager = new SupplierPersistor();

	private static Manager manager = null;

	private Manager() {
	}

	public static Manager getInstance() {
		if (manager == null)
			manager = new Manager();
		return manager;
	}

	public Account getLoggedInAccount() {
		return loggedInAccount;
	}

	public String login(String username, char[] input) throws Exception {

		try {
			Account acc = accountManager.getAccount(username);

			if (acc != null) {
				if (acc.comparePassword(input)) {
					loggedInAccount = acc;
					return "true";
				} else {
					// System.out.println("Password is incorrect!");
					return "passInc";
					// throw new Exception("Password is incorrect!");
				}
			} else {
				// System.out.println("Account was not found!");
				return "accNF";
				// throw new Exception("Account was not found!");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "false";
	}

	public void logout() throws Exception {
		loggedInAccount = null;
	}

}
