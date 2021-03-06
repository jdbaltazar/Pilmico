package gui;

import gui.panels.LoginPanel;
import gui.panels.MenuPanel;
import gui.panels.TablePanel;
import gui.panels.TableUtilPanel;
import gui.panels.extra.AddEntryPanel;
import gui.panels.extra.EditPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import util.ColumnSizer;
import util.DateFormatter;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyPanel;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.entity.deposit.Bank;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.inventorysheet.InventorySheet;
import common.entity.inventorysheet.InventorySheetData;
import common.entity.log.Log;
import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.Employee;
import common.entity.profile.Person;
import common.entity.pullout.PullOut;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;
import common.entity.supplier.Supplier;
import common.manager.Manager;

public class CenterPanel extends SoyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2776395185621879934L;
	private TableUtilPanel tableUtilPanel;

	public CenterPanel() {
		super();
		init();
		addComponents();
		Values.centerPanel = this;

		System.out.println("CenterPanel loaded.");
	}

	private void init() {
		setLayout(new BorderLayout());
		new EditPanel();
		new MenuPanel();
	}

	private void addComponents() {

		add(Values.loginPanel);

		// jd -> pyAmxijgjj7EEhIrn+JgRQ==
		// mine -> K7H4xFqWVe0bKXypGARJvQ==

		/*try {
			Manager.getInstance().login("manager", "pilmico".toCharArray());
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		changeTable(Values.HOME);*/

	}

	public void changeTable(int val) {

		tableUtilPanel = null;
		// if (val != Values.HOME)
		remove(getComponent(getComponentCount() - 1));

		// remove(getComponent(0));

		// System.out.println("component count: "+getComponentCount());

		switch (val) {

		case Values.HOME:
			// add(tableUtilPanel, BorderLayout.PAGE_END);

			add(new AddEntryPanel(), BorderLayout.PAGE_START);
			add(Values.editPanel, BorderLayout.LINE_START);
			add(Values.menuPanel, BorderLayout.PAGE_END);
			fillProducts();

			ColumnSizer.resizeColumns(Values.tablePanel.getSoyTable(), Values.PRODUCTS);

			break;

		case Values.PRODUCTS:

			fillProducts();

			break;

		case Values.PULLOUT:

			fillPullout();
			break;

		case Values.ACCOUNT_RECEIVABLES:
			fillAR();

			break;

		case Values.AR_PAYMENTS:
			fillAR_Payments();
			break;

		case Values.DELIVERY:
			fillDelivery();
			break;

		case Values.SUPPLIERS:
			fillSuppliers();
			break;

		case Values.CASH_ADVANCE:
			fillCashAdvance();
			break;

		case Values.SALARY:
			fillSalary();
			break;

		case Values.CUSTOMERS:
			fillCustomers();
			break;

		case Values.EMPLOYEES:
			fillEmployees();
			break;

		case Values.ACCOUNTS:
			fillAccounts();
			break;

		case Values.SALES:
			fillSales();
			break;

		case Values.EXPENSES:
			fillExpenses();
			break;

		case Values.DISCOUNTS:
			fillDiscounts();
			break;

		case Values.DEPOSITS:
			fillDeposits();
			break;

		case Values.BANK:
			fillBank();
			break;

		case Values.LOGS:
			fillLogs();
			break;

		case Values.CA_PAYMENTS:
			fillCAPayments();
			break;

		case Values.INVENTORY_SHEET:
			fillInventories();
			break;

		case Values.SALES_GRAPH:
			add(Values.linePlot, BorderLayout.CENTER);
			break;

		default:
			break;

		}

		if (val != Values.HOME)
			ColumnSizer.resizeColumns(Values.tablePanel.getSoyTable(), val);

		// repaint();
		// validate();
		revalidate();
		updateUI();
		
	}

	private void fillInventories() {
		try {
			String[] headers = { "IS No", "Date", "Cash on Hand", "Over/Short", "Amount", "Issued by" };
			List<InventorySheetData> isds = Manager.getInstance().getInventorySheetDataManager().getInventorySheetsData();
			List<InventorySheet> iss = new ArrayList<InventorySheet>();
			String[][] entries = new String[isds.size()][headers.length];
			int i = 0;
			for (InventorySheetData isd : isds) {
				InventorySheet is = new InventorySheet(isd);
				entries[i][0] = is.getId() + "";
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.DMYFormat).format(is.getDate());
				entries[i][2] = String.format("%.2f", is.getActualCashOnHand());
				entries[i][3] = InventorySheet.overOrShort(is.getActualCashOnHand(), is.getActualCashCount());
				entries[i][4] = String.format("%.2f", Math.abs(is.getActualCashOnHand() - is.getActualCashCount()));
				entries[i][5] = is.getIssuedBy().getFirstPlusLastName();
				iss.add(is);
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, iss, Tables.INVENTORY_SHEET), Tables.INVENTORY_SHEET);
			
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillExpenses() {
		try {
			String[] headers = { "ID", "IS No", "Date", "Amount", "Expense Type", "Issued by", "Valid?" };
			List<DailyExpenses> dailyExpenses = Manager.getInstance().getDailyExpenseManager().getAllDailyExpenses();
			String[][] entries = new String[dailyExpenses.size()][headers.length];
			int i = 0;
			for (DailyExpenses de : dailyExpenses) {
				entries[i][0] = de.getId() + "";
				entries[i][1] = de.getInventorySheetData() == null ? "-" : de.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(de.getDate());
				entries[i][3] = String.format("%.2f", de.getDailyExpensesAmount());
				entries[i][4] = de.getDailyExpensesType().getName();
				entries[i][5] = de.getAccount().getFirstPlusLastName();
				entries[i][6] = de.isValid() ? "Yes" : "No";
				// entries[i][7] = de.getRemarks();
				i++;
			}
			
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, dailyExpenses, Tables.EXPENSES), Tables.EXPENSES);
			
			add(tableUtilPanel, BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillSales() {
		try {
			String[] headers = { "ID", "IS No", "Date", "Amount", "Cashier", "Valid?" };
			// String[][] entries = { { "1", "June 20, 2013",
			// "John David S. Baltazar", "More" }, { "2", "June 18, 2013",
			// "Juan dela Cruz", "" } };

			List<Sales> sales = Manager.getInstance().getSalesManager().getAllSales();
			String[][] entries = new String[sales.size()][headers.length];

			int i = 0;
			for (Sales s : sales) {
				entries[i][0] = s.getId() + "";
				entries[i][1] = s.getInventorySheetData() == null ? "-" : s.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(s.getDate());
				entries[i][3] = String.format("%.2f", s.getSalesAmount());
				entries[i][4] = s.getCashier().getFirstPlusLastName();
				entries[i][5] = s.isValid() ? "Yes" : "No";
				// entries[i][6] = s.getRemarks();
				i++;
			}
			
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, sales, Tables.SALES), Tables.SALES);
			
			add(tableUtilPanel, BorderLayout.CENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillAR_Payments() {
		try {
			String[] headers = { "ID", "AR ID", "IS No", "Date", "Amount", "Issued By", "Valid?" };

			// String[][] entries = { { "1", "June 20, 2012",
			// "John David S. Baltazar", "2500" }, { "2", "January 18, 2012",
			// "Juan dela Cruz", "5000" } };
			List<ARPayment> arPayments = Manager.getInstance().getAccountReceivableManager().getAllARPayments();
			String[][] entries = new String[arPayments.size()][headers.length];

			int i = 0;
			for (ARPayment arp : arPayments) {
				entries[i][0] = arp.getId() + "";
				entries[i][1] = arp.getAccountReceivable().getId() + "";
				entries[i][2] = arp.getInventorySheetData() == null ? "-" : arp.getInventorySheetData().getId() + "";
				entries[i][3] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(arp.getDate());
				entries[i][4] = String.format("%.2f", arp.getAmount());
				entries[i][5] = arp.getIssuedBy().getFirstPlusLastName();
				entries[i][6] = arp.isValid() ? "Yes" : "No";
				// entries[i][7] = arp.getRemarks();
				i++;
			}
			
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, arPayments, Tables.AR_PAYMENTS), Tables.AR_PAYMENTS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillAR() {
		try {
			String[] headers = { "ID", "IS No", "Date", "Customer", "Balance", "Amount", "Issued By", "Valid?" };
			// String[][] entries = { { "1", "June 20, 2013",
			// "John David S. Baltazar", "Juan dela Cruz", "500" },
			// { "2", "June 18, 2013", "Juan dela Cruz", "John David S. Baltazar",
			// "5000" } };

			List<AccountReceivable> accountReceivables = Manager.getInstance().getAccountReceivableManager().getAllAccountReceivables();
			String[][] entries = new String[accountReceivables.size()][headers.length];

			int i = 0;
			for (AccountReceivable ar : accountReceivables) {
				entries[i][0] = "" + ar.getId();
				entries[i][1] = ar.getInventorySheetData() == null ? "-" : ar.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(ar.getDate());
				entries[i][3] = ar.getCustomer().getFirstPlusLastName();
				entries[i][4] = String.format("%.2f", ar.getBalance());
				entries[i][5] = String.format("%.2f", ar.getAmount());
				entries[i][6] = ar.getIssuedBy().getFirstPlusLastName();
				entries[i][7] = ar.isValid() ? "Yes" : "No";
				// entries[i][7] = ar.getRemarks();

				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, accountReceivables, Tables.ACCOUNT_RECEIVABLES), Tables.ACCOUNT_RECEIVABLES);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillPullout() {

		try {
			String[] headers = { "ID", "IS No", "Date", "Amount", "Issued by", "Valid?" };

			// String[][] entries = { { "1", "May 20, 2013",
			// "John David S. Baltazar", "Hog Feeds", "expired" },
			// { "2", "April 18, 2013", "Juan dela Cruz", "Chicken Feeds", "" } };

			List<PullOut> pullOuts = Manager.getInstance().getPullOutManager().getAllPullOuts();
			String[][] entries = new String[pullOuts.size()][headers.length];

			int i = 0;
			for (PullOut pullOut : pullOuts) {
				entries[i][0] = pullOut.getId() + "";
				entries[i][1] = pullOut.getInventorySheetData() == null ? "-" : pullOut.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(pullOut.getDate());
				entries[i][3] = String.format("%.2f", pullOut.getPulloutAmount());
				entries[i][4] = pullOut.getIssuedBy().getFirstPlusLastName();
				entries[i][5] = pullOut.isValid() ? "Yes" : "No";
				// entries[i][6] = pullOut.getRemarks();
				i++;
			}
			
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, pullOuts, Tables.PULLOUTS), Tables.PULLOUTS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillCashAdvance() {
		try {

			String[] headers = { "ID", "IS No", "Date", "Employee", "Balance/Amount", "Issued By", "Valid?" };
			List<CashAdvance> cashAdvances = Manager.getInstance().getCashAdvanceManager().getAllCashAdvances();
			// String[][] entries = { { "1", "June 20, 2013",
			// "John David S. Baltazar", "Juan dela Cruz", "500" },
			// { "2", "June 18, 2013", "Juan dela Cruz", "John David S. Baltazar",
			// "5000" } };
			String[][] entries = new String[cashAdvances.size()][headers.length];
			int i = 0;
			for (CashAdvance ca : cashAdvances) {
				entries[i][0] = ca.getId() + "";
				entries[i][1] = ca.getInventorySheetData() == null ? "-" : ca.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(ca.getDate());
				entries[i][3] = ca.getEmployee().getFirstPlusLastName();
				entries[i][4] = String.format("%.2f", ca.getBalance()) + "/" + String.format("%.2f", ca.getAmount());
				entries[i][5] = ca.getIssuedBy().getFirstPlusLastName();
				entries[i][6] = ca.isValid() ? "Yes" : "No";
				// entries[i][7] = ca.getRemarks();
				i++;
			}

			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, cashAdvances, Tables.CASH_ADVANCE), Tables.CASH_ADVANCE);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillCAPayments() {
		try {
			String[] headers = { "ID", "CA ID", "IS No", "Date", "Amount", "Issued by", "Valid?" };
			// String[][] entries = { { "1", "June 20, 2011",
			// "John David S. Baltazar", "500" }, { "2", "December 18, 2010",
			// "Juan dela Cruz", "5000" } };
			// String[][] entries = new String[1][headers.length];

			List<CAPayment> caps = Manager.getInstance().getCashAdvanceManager().getAllCAPayments();
			String[][] entries = new String[caps.size()][headers.length];

			int i = 0;
			for (CAPayment s : caps) {
				entries[i][0] = s.getId() + "";
				entries[i][1] = s.getCashAdvance().getId() + "";
				entries[i][2] = s.getInventorySheetData() == null ? "-" : s.getInventorySheetData().getId() + "";
				entries[i][3] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(s.getDate());
				entries[i][4] = String.format("%.2f", s.getAmount());
				entries[i][5] = s.getIssuedBy().getFirstPlusLastName();
				entries[i][6] = s.isValid() ? "Yes" : "No";
				// entries[i][7] = s.getRemarks();
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, caps, Tables.CA_PAYMENTS), Tables.CA_PAYMENTS); 
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillSalary() {
		try {

			String[] headers = { "ID", "IS No", "Date", "Employee", "Net Pay", "Cashier", "Valid?" };
			// String[][] entries = { { "1", "June 20, 2011", "Juan dela Cruz",
			// "John David S. Baltazar", "16000" },
			// { "2", "December 18, 2010", "Maria Clara", "Juan dela Cruz", "12000"
			// } };
			// String[][] entries = new String[1][headers.length];

			// include in one of the columns: grosspay, deductions, net pay

			List<SalaryRelease> salaryReleases = Manager.getInstance().getSalaryReleaseManager().getAllSalaryReleases();
			String[][] entries = new String[salaryReleases.size()][headers.length];

			int i = 0;
			for (SalaryRelease sr : salaryReleases) {
				entries[i][0] = sr.getId() + "";
				entries[i][1] = sr.getInventorySheetData() == null ? "-" : sr.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(sr.getDate());
				entries[i][3] = sr.getIssuedFor().getFirstPlusLastName();
				entries[i][4] = String.format("%.2f", sr.getNetAmount());
				entries[i][5] = sr.getIssuedBy().getFirstPlusLastName();
				entries[i][6] = sr.isValid() ? "Yes" : "No";
				// entries[i][7] = sr.getRemarks();
				i++;
			}

			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, salaryReleases, Tables.SALARY), Tables.SALARY);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillDelivery() {

		String[] headers = { "ID", "IS No", "Date", "Amount", "Cashier", "Valid?" };
		// String[][] entries = { { "1", "June 20, 2013",
		// "John David S. Baltazar", "More" }, { "2", "June 18, 2013",
		// "Juan dela Cruz", "" } };

		try {

			// String[][] entries = { { "1", "April 18, 2013",
			// "John David S. Baltazar", "" }, { "2", "May 11, 2012",
			// "John David S. Baltazar", "Hoax" } };
			List<Delivery> deliveries = Manager.getInstance().getDeliveryManager().getAllDeliveries();
			String[][] entries = new String[deliveries.size()][headers.length];
			int i = 0;
			for (Delivery d : deliveries) {
				entries[i][0] = d.getId() + "";
				entries[i][1] = d.getInventorySheetData() == null ? "-" : d.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(d.getDate());
				entries[i][3] = String.format("%.2f", d.getDeliveryAmount());
				entries[i][4] = d.getReceivedBy().getFirstPlusLastName();
				entries[i][5] = d.isValid() ? "Yes" : "No";
				// entries[i][6] = d.getRemarks();
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, deliveries, Tables.DELIVERIES), Tables.DELIVERIES);
			
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillSuppliers() {
		try {
			String[] headers = { "ID", "Name", "Address", "Contact No.", "Contact Person" };
			List<Supplier> suppliers = Manager.getInstance().getSupplierManager().getSuppliers();
			String[][] entries = new String[suppliers.size()][headers.length];
			int i = 0;
			for (Supplier s : suppliers) {
				entries[i][0] = s.getId() + "";
				entries[i][1] = s.getName();
				entries[i][2] = s.getAddress();
				entries[i][3] = s.getContactNo();
				Person p = s.getContactPerson();
				entries[i][4] = p != null ? p.getFirstPlusLastName() : "";
				i++;
			}
			// String[][] entries = { { "1", "Rubina Hog Feeds Inc.",
			// "Marikina City", "", "" },
			// { "2", "Universal Feeds Corp.", "Tacloban City", "325-5689",
			// "Pugad Baboy" } };

			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, suppliers, Tables.SUPPLIERS), Tables.SUPPLIERS);
			
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillDiscounts() {
		try {

			String[] headers = { "ID", "IS No", "Date", "Amount", "Issued By", "Valid?" };
			List<DiscountIssue> dis = Manager.getInstance().getDiscountIssueManager().getAllDiscountIssues();
			System.out.println("size of discount issues: " + dis.size());
			String[][] entries = new String[dis.size()][headers.length];

			int i = 0;
			for (DiscountIssue di : dis) {
				entries[i][0] = di.getId() + "";
				entries[i][1] = di.getInventorySheetData() == null ? "-" : di.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(di.getDate());
				entries[i][3] = String.format("%.2f", di.getAmount());
				entries[i][4] = di.getIssuedBy().getFirstPlusLastName();
				entries[i][5] = di.isValid() ? "Yes" : "No";
				// entries[i][6] = di.getRemarks();
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, dis, Tables.DISCOUNTS), Tables.DISCOUNTS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillDeposits() {
		try {

			String[] headers = { "ID", "IS No", "Date", "Acct No, Bank", "Amount", "Depositor", "Issued By", "Valid?" };
			List<Deposit> deposits = Manager.getInstance().getDepositManager().getAllDeposits();
			String[][] entries = new String[deposits.size()][headers.length];

			int i = 0;
			for (Deposit d : deposits) {
				entries[i][0] = d.getId() + "";
				entries[i][1] = d.getInventorySheetData() == null ? "-" : d.getInventorySheetData().getId() + "";
				entries[i][2] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(d.getDate());
				entries[i][3] = d.getBankAccount().getAccountNo() + ", " + d.getBankAccount().getBank().getName();
				entries[i][4] = String.format("%.2f", d.getAmount());
				entries[i][5] = d.getDepositor().getFirstPlusLastName();
				entries[i][6] = d.getIssuedBy().getFirstPlusLastName();
				entries[i][7] = d.isValid() ? "Yes" : "No";
				// entries[i][8] = d.getRemarks();
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, deposits, Tables.DEPOSITS), Tables.DEPOSITS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillBank() {
		try {
			String[] headers = { "ID", "Name", "Address", "Contact No.", "Account No(s)" };
			List<Bank> banks = Manager.getInstance().getDepositManager().getBanks();
			String[][] entries = new String[banks.size()][headers.length];
			int i = 0;
			for (Bank p : banks) {
				entries[i][0] = p.getId() + "";
				entries[i][1] = p.getName();
				entries[i][2] = p.getAddress();
				entries[i][3] = p.getContactNo();
				entries[i][4] = p.getAccountNos();
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, banks, Tables.BANK), Tables.BANK);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillCustomers() {
		try {
			String[] headers = { "ID", "Name", "Address", "Contact No.", "Balance" };

			List<Person> customers = Manager.getInstance().getEmployeePersonManager().getCustomersOnly();

			// String[][] entries = { { "1", "Baltazar", "John David", "S",
			// "09161429583" }, { "2", "dela Cruz", "Juan", "B", "09161234567" } };
			String[][] entries = new String[customers.size()][headers.length];
			int i = 0;
			for (Person p : customers) {
				entries[i][0] = p.getId() + "";
				entries[i][1] = p.getFirstPlusLastName();
				entries[i][2] = p.getAddress();
				entries[i][3] = p.getContactNo();
				entries[i][4] = String.format("%.2f", p.getTotalAccountReceivables());
				i++;
			}
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, customers, Tables.CUSTOMERS), Tables.CUSTOMERS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillEmployees() {
		try {
			String[] headers = { "ID", "Designation", "Name", "Contact No", "Salary", "Status" };

			List<Employee> employees = Manager.isAuthorized() ? Manager.getInstance().getEmployeePersonManager().getAllEmployees() : Manager
					.getInstance().getEmployeePersonManager().getAllEmployeesExceptManagers();

			String[][] entries = new String[employees.size()][headers.length];
			// String[][] entries = { { "1", "John David Baltazar", "Janitor",
			// "Active", "" }, { "2", "Juan dela Cruz", "Sales Clerk",
			// "Terminated", "" } };
			int i = 0;
			for (Employee e : employees) {
				entries[i][0] = e.getId() + "";
				entries[i][1] = e.getDesignation().getName();
				entries[i][2] = e.getFirstPlusLastName();
				entries[i][3] = e.getContactNo();
				entries[i][4] = String.format("%.2f", e.getSalary());
				entries[i][5] = e.getStatus().getStatus();
				i++;
			}
			
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, employees, Tables.EMPLOYEES), Tables.EMPLOYEES);
			add(tableUtilPanel, BorderLayout.CENTER);
			// System.out.println("employees again here");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillAccounts() {
		try {
			String[] headers = { "ID", "Username", "Account Type", "Designation", "Name", "Status" };
			List<Account> accounts = Manager.isAuthorized() ? Manager.getInstance().getAccountManager().getAccounts() : Manager.getInstance()
					.getAccountManager().getAccountThenAddToList(Manager.loggedInAccount.getId());
			String[][] entries = new String[accounts.size()][headers.length];
			int i = 0;
			for (Account a : accounts) {
				entries[i][0] = a.getId() + "";
				entries[i][1] = a.getUsername();
				entries[i][2] = a.getAccountType().getName();
				entries[i][3] = a.getEmployee().getDesignation().getName();
				entries[i][4] = a.getEmployee().getFirstPlusLastName();
				entries[i][5] = a.getEmployee().getStatus().getStatus();
				i++;
			}
			
			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, accounts, Tables.ACCOUNTS), Tables.ACCOUNTS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillProducts() {

		try {
			String[] headers = { "ID", "Name", "Kls/SK", "SK", "Kg", "Price/SK", "Price/Kg", "Category", "Available?" };
			List<Product> products = Manager.getInstance().getProductManager().getProducts();
			String[][] entries = new String[products.size()][headers.length];

			int i = 0;
			for (Product p : products) {
				entries[i][0] = p.getId() + "";
				entries[i][1] = p.getName();
				entries[i][2] = p.getKilosPerSackDescription();
				entries[i][3] = p.getSacksDescription();
				entries[i][4] = p.getKilosOnDisplayDescription();
				entries[i][5] = String.format("%.2f", p.getCurrentPricePerSack());
				entries[i][6] = String.format("%.2f", p.getCurrentPricePerKilo());
				entries[i][7] = p.getCategory().getName();
				entries[i][8] = p.isAvailable() ? "Yes" : "No";
				i++;
			}

			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, products, Tables.PRODUCTS), Tables.PRODUCTS);
			add(tableUtilPanel, BorderLayout.CENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillLogs() {

		try {
			String[] headers = { "Date", "Description" };
			List<Log> logs = Manager.getInstance().getLogManager().getLogsRecentFirst();
			String[][] entries = new String[logs.size()][headers.length];
			// String[][] entries = new String[1][headers.length];
			int i = 0;
			for (Log l : logs) {
				entries[i][0] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(l.getDate());
				entries[i][1] = l.getDescription();
				i++;
			}

			Values.tablePanel.getSoyTable().getColumnModel().getColumn(0).setMinWidth(200);
			Values.tablePanel.getSoyTable().getColumnModel().getColumn(0).setMaxWidth(200);

			tableUtilPanel = new TableUtilPanel(new TablePanel(entries, headers, logs, Tables.LOGS), Tables.LOGS);
			add(tableUtilPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void logout() {
		
		tableUtilPanel = null;
		
		removeAll();
		add(new LoginPanel(), BorderLayout.CENTER);
		
		updateUI();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		/*
		 * gradient = new GradientPaint(0, 0, new Color(30,30,30),0, getHeight(),
		 * new Color(5,5,5)); g2.setPaint(gradient);
		 */
		gradient = new GradientPaint(0, 0, new Color(250, 250, 250), 0, getHeight(), new Color(240, 240, 240));
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setPaint(new Color(210, 210, 210));
		g2.fillRect(0, 0, g.getClipBounds().width, 1);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

}
