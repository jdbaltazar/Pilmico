package common.manager;

import common.entity.log.Log;
import common.entity.log.LogType;
import common.entity.profile.Account;
import common.entity.profile.AccountType;

import core.persist.AccountPersistor;
import core.persist.AccountReceivablePersistor;
import core.persist.CashAdvancePersistor;
import core.persist.DailyExpensePersistor;
import core.persist.DeliveryPersistor;
import core.persist.DepositPersistor;
import core.persist.DiscountIssuePersistor;
import core.persist.EmployeePersonPersistor;
import core.persist.InventorySheetDataPersistor;
import core.persist.LogPersistor;
import core.persist.NotePersistor;
import core.persist.ProductPersistor;
import core.persist.PullOutPersistor;
import core.persist.SalaryReleasePersistor;
import core.persist.SalesPersistor;
import core.persist.StorePersistor;
import core.persist.SupplierPersistor;

public class Manager {

	public static Account loggedInAccount = null;

	private AccountManager accountManager = null;
	private AccountReceivableManager accountReceivableManager = null;
	private CashAdvanceManager cashAdvanceManager = null;
	private DailyExpensesManager dailyExpenseManager = null;
	private DeliveryManager deliveryManager = null;
	private DepositManager depositManager = null;
	private DiscountIssueManager discountIssueManager = null;
	private EmployeePersonManager employeePersonManager = null;
	private InventorySheetDataManager inventorySheetDataManager = null;
	private LogManager logManager = null;
	private NoteManager noteManager = null;
	private ProductManager productManager = null;
	private PullOutManager pullOutManager = null;
	private SalesManager salesManager = null;
	private SalaryReleaseManager salaryReleaseManager = null;
	private StoreManager storeManager = null;
	private SupplierManager supplierManager = null;

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

	public static boolean isLoggedIn() {
		return loggedInAccount != null;
	}

	public String login(String username, char[] input) throws Exception {

		// Date date = new Date();
		// Calendar d1 = Calendar.getInstance();
		// d1.setTime(date);
		// d1.set(Calendar.YEAR, 2013);
		// d1.set(Calendar.MONTH, Calendar.AUGUST);
		// d1.set(Calendar.DATE, 31);
		// date = d1.getTime();
		// date = DateTool.getDateWithoutTime(date);
		//
		// Date today = new Date();
		// if (today.before(date)) {

		try {

			accountManager = getAccountManager();
			Account acc = accountManager.getAccount(username);

			if (acc != null && acc.isActive()) {
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
		// } else {
		//
		// JOptionPane.showMessageDialog(Values.mainFrame,
		// "Trial period ended! \nPlease contact system administrator", "Notice",
		// JOptionPane.INFORMATION_MESSAGE);
		// return "Trial period ended";
		// }
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

		String desc = Manager.loggedInAccount.getDesignationPlusFirstPlusLastName() + " logged out";
		Log log = new Log(new LogType(LogType.SYSTEM), desc);
		Manager.getInstance().getLogManager().addLog(log);

		loggedInAccount = null;

		accountManager = null;
		accountReceivableManager = null;
		cashAdvanceManager = null;
		dailyExpenseManager = null;
		deliveryManager = null;
		depositManager = null;
		discountIssueManager = null;
		employeePersonManager = null;
		inventorySheetDataManager = null;
		logManager = null;
		noteManager = null;
		productManager = null;
		pullOutManager = null;
		salesManager = null;
		salaryReleaseManager = null;
		storeManager = null;
		supplierManager = null;

		manager = null;
	}

	public AccountManager getAccountManager() {
		if (accountManager == null)
			accountManager = new AccountPersistor();
		return accountManager;
	}

	public AccountReceivableManager getAccountReceivableManager() {
		if (accountReceivableManager == null)
			accountReceivableManager = new AccountReceivablePersistor();
		return accountReceivableManager;
	}

	public CashAdvanceManager getCashAdvanceManager() {
		if (cashAdvanceManager == null)
			cashAdvanceManager = new CashAdvancePersistor();
		return cashAdvanceManager;
	}

	public DailyExpensesManager getDailyExpenseManager() {
		if (dailyExpenseManager == null)
			dailyExpenseManager = new DailyExpensePersistor();
		return dailyExpenseManager;
	}

	public DeliveryManager getDeliveryManager() {
		if (deliveryManager == null)
			deliveryManager = new DeliveryPersistor();
		return deliveryManager;
	}

	public DepositManager getDepositManager() {
		if (depositManager == null)
			depositManager = new DepositPersistor();
		return depositManager;
	}

	public DiscountIssueManager getDiscountIssueManager() {
		if (discountIssueManager == null)
			discountIssueManager = new DiscountIssuePersistor();
		return discountIssueManager;
	}

	public EmployeePersonManager getEmployeePersonManager() {
		if (employeePersonManager == null)
			employeePersonManager = new EmployeePersonPersistor();
		return employeePersonManager;
	}

	public InventorySheetDataManager getInventorySheetDataManager() {
		if (inventorySheetDataManager == null)
			inventorySheetDataManager = new InventorySheetDataPersistor();
		return inventorySheetDataManager;
	}

	public LogManager getLogManager() {
		if (logManager == null)
			logManager = new LogPersistor();
		return logManager;
	}

	public NoteManager getNoteManager() {
		if (noteManager == null)
			noteManager = new NotePersistor();
		return noteManager;
	}

	public ProductManager getProductManager() {
		if (productManager == null)
			productManager = new ProductPersistor();
		return productManager;
	}

	public PullOutManager getPullOutManager() {
		if (pullOutManager == null)
			pullOutManager = new PullOutPersistor();
		return pullOutManager;
	}

	public SalesManager getSalesManager() {
		if (salesManager == null)
			salesManager = new SalesPersistor();
		return salesManager;
	}

	public SalaryReleaseManager getSalaryReleaseManager() {
		if (salaryReleaseManager == null)
			salaryReleaseManager = new SalaryReleasePersistor();
		return salaryReleaseManager;
	}

	public StoreManager getStoreManager() {
		if (storeManager == null)
			storeManager = new StorePersistor();
		return storeManager;
	}

	public SupplierManager getSupplierManager() {
		if (supplierManager == null)
			supplierManager = new SupplierPersistor();
		return supplierManager;
	}

	public Manager getManager() {
		return manager;
	}

}
