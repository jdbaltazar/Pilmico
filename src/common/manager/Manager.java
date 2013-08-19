package common.manager;

import gui.forms.util.DateTool;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import util.Values;

import common.entity.profile.Account;
import common.entity.profile.AccountType;

import core.database.DatabaseTool;
import core.persist.AccountPersistor;
import core.persist.AccountReceivablePersistor;
import core.persist.CashAdvancePersistor;
import core.persist.DailyExpensePersistor;
import core.persist.DeliveryPersistor;
import core.persist.DepositPersistor;
import core.persist.DiscountIssuePersistor;
import core.persist.EmployeePersonPersistor;
import core.persist.InventorySheetDataPersistor;
import core.persist.ProductPersistor;
import core.persist.LogPersistor;
import core.persist.NotePersistor;
import core.persist.PullOutPersistor;
import core.persist.SalaryReleasePersistor;
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

	public static DepositManager depositManager = new DepositPersistor();

	public static DiscountIssueManager discountIssueManager = new DiscountIssuePersistor();

	public static EmployeePersonManager employeePersonManager = new EmployeePersonPersistor();

	public static InventorySheetDataManager inventorySheetDataManager = new InventorySheetDataPersistor();

	public static LogManager logManager = new LogPersistor();

	public static NoteManager noteManager = new NotePersistor();

	public static ProductManager productManager = new ProductPersistor();

	public static PullOutManager pullOutManager = new PullOutPersistor();

	public static SalesManager salesManager = new SalesPersistor();

	public static SalaryReleaseManager salaryReleaseManager = new SalaryReleasePersistor();

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

		Date date = new Date();
		Calendar d1 = Calendar.getInstance();
		d1.setTime(date);
		d1.set(Calendar.YEAR, 2013);
		d1.set(Calendar.MONTH, Calendar.AUGUST);
		d1.set(Calendar.DATE, 30);
		date = d1.getTime();
		date = DateTool.getDateWithoutTime(date);

		Date today = new Date();
		if (today.before(date)) {

			try {
				Account acc = accountManager.getAccount(username);

				if (acc != null && acc.isActive()) {
					if (acc.comparePassword(input)) {
						loggedInAccount = acc;
						DatabaseTool.executeUpdates();
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
		} else {

			JOptionPane.showMessageDialog(Values.mainFrame, "Trial period ended! \nPlease contact system administrator", "Notice",
					JOptionPane.INFORMATION_MESSAGE);
			return "Trial period ended";
		}
		return "false";
	}

	public static boolean isAuthorized() {
		if (Manager.loggedInAccount != null) {
			if (loggedInAccount.getAccountType().getName().equals(AccountType.systemAdmin)
					|| loggedInAccount.getAccountType().getName().equals(AccountType.manager)) {
				if (Manager.loggedInAccount.isActive())
					return true;
			}
		}

		return false;
	}

	public void logout() throws Exception {
		loggedInAccount = null;
	}

}
