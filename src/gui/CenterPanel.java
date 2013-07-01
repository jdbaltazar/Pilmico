package gui;

import gui.panels.LeftPanel;
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
import java.util.List;

import util.DateFormatter;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyPanel;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.entity.product.Product;
import common.entity.pullout.PullOut;
import common.entity.salary.CashAdvance;
import common.entity.sales.Sales;
import common.entity.supplier.Supplier;
import common.manager.Manager;

public class CenterPanel extends SoyPanel {

	private LeftPanel leftPanel;
	private TableUtilPanel tableUtilPanel;
	private String[] columnNames = { "Name", "Unit", "Selling Price",
			"Purchase Price", "Quantity", "Item Category" };
	private Object[][] data = {
			{ "The Bar", "Bottle", "65.00", "55.00", "10", "Liquor" },
			{ "Lucky Me!", "Piece", "8.50", "7.50", "50", "Noodles" },
			{ "Chupa chups", "Piece", "8.00", "7.50", "50", "Lollipop" },
			{ "Nature's Spring", "Bottle", "10.00", "8.50", "10",
					"Mineral Water" },
			{ "Purefoods", "Pack", "45.50", "44.30", "10", "Frozen Foods" } };
	//
	// private String[] columnNames2 = { "First Name", "Last Name", "Sport" };
	// private Object[][] data2 = { { "Neil", "Ybanez", "Snorwboarding" }, {
	// "John", "Doe", "Rowing" }, { "Sue", "Black", "Knitting" },
	// { "Jane", "White", "Speed reading dfasdasd adasdas" }, { "Joe", "Brown",
	// "Pool" } };
	//
	// private String[] supplierHeader = { "Name", "Address", "Contact Person"
	// };
	// private Object[][] suppliers = { { "Universal Rubina",
	// "Quezon City, Philippines", "Rubina D. Rubina" },
	// { "Jack n Jill", "Paranaque, Philippines", "Jack N. Jill" } };

	private TablePanel tablePanel;

	public CenterPanel() {
		super();
		init();
		addComponents();
		Values.centerPanel = this;
	}

	private void init() {
		setLayout(new BorderLayout());

		// tablePanel = new TablePanel(data, columnNames, null);
	}

	private void addComponents() {
		// leftPanel = new LeftPanel();
		// tableUtilPanel = new TableUtilPanel(tablePanel, "STOCKS");
		// add(searchPanel, BorderLayout.PAGE_END);
		// add(new BottomPanel(), BorderLayout.PAGE_END);
		/*
		 * Values.searchPanel = searchPanel;
		 * 
		 * bookPanel = new BookPanel(); logPanel = new LogPanel(); homePanel =
		 * new HomePanel();
		 */
		// add(leftPanel, BorderLayout.LINE_START);
		// add(new TablePanel(data, columnNames), BorderLayout.CENTER);

//		 add(new LoginPanel());
		// menuPanel.setBounds(0, 0, Values.WIDTH, 400);

		/*
		 * add(new AddEntryPanel(), BorderLayout.PAGE_START); add(new
		 * EditPanel(), BorderLayout.LINE_START); add(new MenuPanel(),
		 * BorderLayout.PAGE_END); fillItems();
		 */

		changeTable(Values.HOME);
		/*
<<<<<<< HEAD
		 * add(new AddEntryPanel(), BorderLayout.PAGE_START); add(new
		 * EditPanel(), BorderLayout.LINE_START); add(new MenuPanel(),
		 * BorderLayout.PAGE_END); add(tableUtilPanel, BorderLayout.CENTER);
=======
		 * add(new AddEntryPanel(), BorderLayout.PAGE_START); add(new EditPanel(),
		 * BorderLayout.LINE_START); add(new MenuPanel(), BorderLayout.PAGE_END);
		 * add(tableUtilPanel, BorderLayout.CENTER);
>>>>>>> refs/remotes/origin/master
		 */

	}

	public void changeTable(int val) {

		if (val != Values.HOME)
			remove(getComponent(getComponentCount() - 1));

		switch (val) {

		case Values.HOME:
			// add(tableUtilPanel, BorderLayout.PAGE_END);
			add(new AddEntryPanel(), BorderLayout.PAGE_START);
			add(new EditPanel(), BorderLayout.LINE_START);
			add(new MenuPanel(), BorderLayout.PAGE_END);
			fillProducts();
			break;

		case Values.PRODUCTS:
			// add(tableUtilPanel, BorderLayout.PAGE_END);
			fillProducts();
			break;

		case Values.PULLOUT:
			// add(tableUtilPanel, BorderLayout.PAGE_END);
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

		case Values.PROFILES:
			fillProfiles();
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

		case Values.LOGS:
			fillLogs();
			break;
		/*
		 * case Values.SALES_ORDER: fillSalesOrder(); break;
		 * 
		 * 
		 * case Values.PURCHASE: fillStockPurchase(); break;
		 */

		default:
			// fillLogs();
			fillNULLForm();
			break;

		}

		// repaint();
		// validate();
		revalidate();
		updateUI();
	}

	private void fillNULLForm() {
		try {
			String[] headers = { "N / A" };
			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.INVENTORY_SHEET), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void fillExpenses() {
		try {
			String[] headers = { "ID", "Date", "Expense Type", "Issued by" };
			List<DailyExpenses> dailyExpenses = Manager.dailyExpenseManager.getDailyExpenses();
			// String[][] entries = { { "1", "June 20, 2013", "Type A",
			// "John David S. Baltazar" }, { "2", "June 18, 2013", "Type B",
			// "Juan dela Cruz" } };
			String[][] entries = new String[dailyExpenses.size()][headers.length];
			int i = 0;
			for (DailyExpenses de : dailyExpenses) {
				entries[i][0] = de.getId() + "";
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.MDYFormat).format(de.getDate());
				entries[i][2] = de.getDailyExpensesType().getName();
				entries[i][3] = de.getAccount().getEmployee().getFirstPlusLastName();
				i++;
			}
			add(new TableUtilPanel(new TablePanel(entries, headers, dailyExpenses), Tables.EXPENSES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillSales() {
		try {
			String[] headers = { "ID", "Date", "Amount", "Cashier", "Remarks" };
			// String[][] entries = { { "1", "June 20, 2013",
			// "John David S. Baltazar", "More" }, { "2", "June 18, 2013",
			// "Juan dela Cruz", "" } };

			List<Sales> sales = Manager.salesManager.getSaless();
			String[][] entries = new String[sales.size()][headers.length];

			int i = 0;
			for (Sales s : sales) {
				entries[i][0] = s.getId() + "";
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(s.getDate());
				entries[i][2] = s.getSalesAmount() + "";
				entries[i][3] = s.getCashier().getEmployee().getFirstPlusLastName();
				entries[i][4] = s.getRemarks();
				i++;
			}
			add(new TableUtilPanel(new TablePanel(entries, headers, sales), Tables.SALES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillAR_Payments() {
		try {
			String[] headers = { "ID", "AR ID", "Date", "Amount", "Issued By" };
			// String[][] entries = { { "1", "June 20, 2012",
			// "John David S. Baltazar", "2500" }, { "2", "January 18, 2012",
			// "Juan dela Cruz", "5000" } };
			List<ARPayment> arPayments = Manager.accountReceivableManager.getARPayments();
			String[][] entries = new String[arPayments.size()][headers.length];

			int i = 0;
			for (ARPayment arp : arPayments) {
				entries[i][0] = arp.getId() + "";
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(arp.getDate());
				entries[i][2] = arp.getAmount() + "";
				entries[i][3] = arp.getIssuedBy().getEmployee().getFirstPlusLastName();
				i++;
			}
			add(new TableUtilPanel(new TablePanel(entries, headers, arPayments), Tables.AR_PAYMENTS), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillAR() {
		try {
			String[] headers = { "ID", "Date", "Customer", "Balance", "Issued By" };
			// String[][] entries = { { "1", "June 20, 2013",
			// "John David S. Baltazar", "Juan dela Cruz", "500" },
			// { "2", "June 18, 2013", "Juan dela Cruz", "John David S. Baltazar",
			// "5000" } };

			List<AccountReceivable> accountReceivables = Manager.accountReceivableManager.getAccountReceivables();
			String[][] entries = new String[accountReceivables.size()][headers.length];

			int i = 0;
			for (AccountReceivable ar : accountReceivables) {
				entries[i][0] = "" + ar.getId();
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(ar.getDate());
				entries[i][2] = ar.getCustomer().getFirstPlusLastName();
				entries[i][3] = ar.getBalance() + "";
				entries[i][4] = ar.getIssuedBy().getEmployee().getFirstPlusLastName();
				i++;
			}
			add(new TableUtilPanel(new TablePanel(entries, headers, accountReceivables), Tables.ACCOUNT_RECEIVABLES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillPullout() {
		try {
			String[] headers = { "ID", "Date", "Amount", "Issued By" };

			// String[][] entries = { { "1", "May 20, 2013",
			// "John David S. Baltazar", "Hog Feeds", "expired" },
			// { "2", "April 18, 2013", "Juan dela Cruz", "Chicken Feeds", "" } };

			List<PullOut> pullOuts = Manager.pullOutManager.getPullOuts();
			String[][] entries = new String[pullOuts.size()][headers.length];

			int i = 0;
			for (PullOut pullOut : pullOuts) {
				entries[i][0] = pullOut.getId() + "";
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(pullOut.getDate());
				entries[i][2] = pullOut.getPulloutAmount() + "";
				entries[i][3] = pullOut.getIssuedBy().getEmployee().getFirstPlusLastName();
				i++;
			}
			add(new TableUtilPanel(new TablePanel(entries, headers, pullOuts), Tables.PULLOUT), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillCashAdvance() {
		try {
			String[] headers = { "ID", "Date", "Amount", "Employee", "Balance", "Issued By" };
			List<CashAdvance> cashAdvances = Manager.cashAdvanceManager.getCashAdvances();
			// String[][] entries = { { "1", "June 20, 2013",
			// "John David S. Baltazar", "Juan dela Cruz", "500" },
			// { "2", "June 18, 2013", "Juan dela Cruz", "John David S. Baltazar",
			// "5000" } };
			String[][] entries = new String[cashAdvances.size()][headers.length];
			int i = 0;
			for (CashAdvance ca : cashAdvances) {
				entries[i][0] = ca.getId() + "";
				entries[i][1] = DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(ca.getDate());
				entries[i][2] = ca.getAmount() + "";
				entries[i][3] = ca.getEmployee().getFirstPlusLastName();
				entries[i][4] = ca.getBalance() + "";
				entries[i][5] = ca.getIssuedBy().getEmployee().getFirstPlusLastName();

				i++;
			}

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.CASH_ADVANCE), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillSalary() {
		try {
			String[] headers = { "ID", "Date", "Issued by", "Issued for",
					"Amount" };
			String[][] entries = {
					{ "1", "June 20, 2011", "Juan dela Cruz",
							"John David S. Baltazar", "16000" },
					{ "2", "December 18, 2010", "Maria Clara",
							"Juan dela Cruz", "12000" } };
			// String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.SALARY), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillStockPurchase() {

		try {

			String[] headers = { "Puchase No", "Date", "Supplier" };
			String[][] entries = {
					{ "1", "June 20, 2013", "Universal Feeds Corp." },
					{ "2", "June 18, 2013", "Rubina Foods Inc." } };
			// String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					"STOCK PURCHASE"), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillDelivery() {
		try {
			String[] headers = { "ID", "Date", "Amount", "Received by" };
			// String[][] entries = { { "1", "April 18, 2013",
			// "John David S. Baltazar", "" }, { "2", "May 11, 2012",
			// "John David S. Baltazar", "Hoax" } };
			List<Delivery> deliveries = Manager.deliveryManager.getDeliveries();
			String[][] entries = new String[deliveries.size()][headers.length];
			int i = 0;
			for (Delivery d : deliveries) {
				entries[i][0] = d.getId() + "";
				entries[i][0] = DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(d.getDate());
				entries[i][0] = d.getDeliveryAmount() + "";
				entries[i][0] = d.getReceivedBy().getEmployee().getFirstPlusLastName();
				i++;
			}

			add(new TableUtilPanel(new TablePanel(entries, headers, deliveries), Tables.DELIVERY), BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillSuppliers() {
		try {
			String[] headers = { "ID", "Name", "Address", "Contact No.", "Contact Person" };
			List<Supplier> suppliers = Manager.supplierManager.getSuppliers();
			String[][] entries = new String[suppliers.size()][headers.length];
			int i = 0;
			for (Supplier s : suppliers) {
				entries[i][0] = s.getId() + "";
				entries[i][1] = s.getName();
				entries[i][2] = s.getAddress();
				entries[i][3] = s.getContactNo();
				entries[i][4] = s.getContactPerson().getFirstPlusLastName();
				i++;
			}
			// String[][] entries = { { "1", "Rubina Hog Feeds Inc.",
			// "Marikina City", "", "" },
			// { "2", "Universal Feeds Corp.", "Tacloban City", "325-5689",
			// "Pugad Baboy" } };

			add(new TableUtilPanel(new TablePanel(entries, headers, suppliers), Tables.SUPPLIERS), BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillSalesOrder() {
		try {

			String[] headers = { "Sales No.", "Date", "Cashier" };
			String[][] entries = { { "1", "June 8, 2013", "Juan dela Cruz" },
					{ "2", "May 27, 2013", "Juan dela Cruz" } };
			// String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					"SALES ORDER"), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillProfiles() {
		try {
			String[] headers = { "ID", "Last Name", "First Name",
					"Middle Name", "Contact No." };
			String[][] entries = {
					{ "1", "Baltazar", "John David", "S", "09161429583" },
					{ "2", "dela Cruz", "Juan", "B", "09161234567" } };
			// String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.PROFILES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillEmployees() {
		try {
			String[] headers = { "ID", "Name", "Designation", "Status",
					"Remarks" };
			// String[][] entries = new String[1][headers.length];
			String[][] entries = {
					{ "1", "John David Baltazar", "Janitor", "Active", "" },
					{ "2", "Juan dela Cruz", "Sales Clerk", "Terminated", "" } };

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.EMPLOYEES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillAccounts() {
		try {
			String[] headers = { "Account No", "Username", "Account Type",
					"Name", "Contact No" };
			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.ACCOUNTS), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillProducts() {

		try {
			// String[][] entries = new String[1][headers.length];
			String[] headers = { "ID", "Name", "SK", "Kilo", "Price/SK", "Price/Kilo", "Category", "Available?" };
			List<Product> products = Manager.productManager.getProducts();
			String[][] entries = new String[products.size()][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					Tables.PRODUCTS), BorderLayout.CENTER);
			int i = 0;
			for (Product p : products) {
				entries[i][0] = p.getId() + "";
				entries[i][1] = p.getName();
				entries[i][2] = p.getQuantityInSack() + "";
				entries[i][3] = p.getQuantityInKilo() + "";
				entries[i][4] = p.getPricePerSack() + "";
				entries[i][5] = p.getPricePerKilo() + "";
				entries[i][6] = p.getCategory().getName();
				entries[i][7] = p.isAvailable() ? "Yes" : "No";
				i++;
			}

			add(new TableUtilPanel(new TablePanel(entries, headers, products), Tables.PRODUCTS), BorderLayout.CENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void fillLogs() {

		try {
			String[] headers = { "Date", "Description" };
			String[][] entries = { { "June 21, 2013", "dummy_acct logged in" } };
			// String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null),
					"LOGS"), BorderLayout.CENTER);

			Values.tablePanel.getSoyTable().getColumnModel().getColumn(0)
					.setMinWidth(200);
			Values.tablePanel.getSoyTable().getColumnModel().getColumn(0)
					.setMaxWidth(200);
			
			add(new TableUtilPanel(new TablePanel(entries, headers, null), "LOGS"), BorderLayout.CENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logout() {
		removeAll();
		add(new LoginPanel());
		updateUI();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		/*
		 * gradient = new GradientPaint(0, 0, new Color(30,30,30),0,
		 * getHeight(), new Color(5,5,5)); g2.setPaint(gradient);
		 */
		gradient = new GradientPaint(0, 0, new Color(250, 250, 250), 0,
				getHeight(), new Color(240, 240, 240));
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setPaint(new Color(210, 210, 210));
		g2.fillRect(0, 0, g.getClipBounds().width, 1);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

}
