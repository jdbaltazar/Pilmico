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

public class CenterPanel extends SoyPanel {

	private LeftPanel leftPanel;
	private TableUtilPanel tableUtilPanel;
	private String[] columnNames = { "Name", "Unit", "Selling Price", "Purchase Price", "Quantity", "Item Category" };
	private Object[][] data = { { "The Bar", "Bottle", "65.00", "55.00", "10", "Liquor" }, { "Lucky Me!", "Piece", "8.50", "7.50", "50", "Noodles" },
			{ "Chupa chups", "Piece", "8.00", "7.50", "50", "Lollipop" }, { "Nature's Spring", "Bottle", "10.00", "8.50", "10", "Mineral Water" },
			{ "Purefoods", "Pack", "45.50", "44.30", "10", "Frozen Foods" } };
	//
	// private String[] columnNames2 = { "First Name", "Last Name", "Sport" };
	// private Object[][] data2 = { { "Neil", "Ybanez", "Snowboarding" }, {
	// "John", "Doe", "Rowing" }, { "Sue", "Black", "Knitting" },
	// { "Jane", "White", "Speed reading dfasdasd adasdas" }, { "Joe", "Brown",
	// "Pool" } };
	//
	// private String[] supplierHeader = { "Name", "Address", "Contact Person" };
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

		//tablePanel = new TablePanel(data, columnNames, null);
	}

	private void addComponents() {
		// leftPanel = new LeftPanel();
		//tableUtilPanel = new TableUtilPanel(tablePanel, "STOCKS");
		// add(searchPanel, BorderLayout.PAGE_END);
		// add(new BottomPanel(), BorderLayout.PAGE_END);
		/*
		 * Values.searchPanel = searchPanel;
		 * 
		 * bookPanel = new BookPanel(); logPanel = new LogPanel(); homePanel = new
		 * HomePanel();
		 */
		// add(leftPanel, BorderLayout.LINE_START);
		// add(new TablePanel(data, columnNames), BorderLayout.CENTER);

//		add(new LoginPanel());
		// menuPanel.setBounds(0, 0, Values.WIDTH, 400);

		/*
		 * add(new AddEntryPanel(), BorderLayout.PAGE_START); add(new EditPanel(),
		 * BorderLayout.LINE_START); add(new MenuPanel(), BorderLayout.PAGE_END);
		 * fillItems();
		 */

		changeTable(Values.HOME);
		 /*add(new AddEntryPanel(), BorderLayout.PAGE_START); add(new EditPanel(),
		 BorderLayout.LINE_START); add(new MenuPanel(), BorderLayout.PAGE_END);
		 add(tableUtilPanel, BorderLayout.CENTER);*/
		
	}

	public void changeTable(int val) {

		if(val != Values.HOME)
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
			
		case Values.CA_PAYMENTS:
			fillCAPayments();
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
		/*case Values.SALES_ORDER:
			fillSalesOrder();
			break;


		case Values.PURCHASE:
			fillStockPurchase();
			break;*/

		default:
//			fillLogs();
			fillNULLForm();
			break;

		}

		// repaint();
		// validate();
		revalidate();
		updateUI();
	}
	
	
	private void fillNULLForm(){
		try {
			String[] headers = { "N / A"};
			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), "NOT APPLICABLE"), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillExpenses(){
		try {
			String[] headers = { "ID", "Date", "Expense Type", "Issued by"};
			String[][] entries = {{"1", "June 20, 2013", "Type A", "John David S. Baltazar"}, {"2", "June 18, 2013", "Type B", "Juan dela Cruz"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.EXPENSES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillSales(){
		try {
			String[] headers = { "ID", "Date", "Cashier", "Remarks"};
			String[][] entries = {{"1", "June 20, 2013", "John David S. Baltazar", "More"}, {"2", "June 18, 2013", "Juan dela Cruz", ""}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.SALES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillAR_Payments(){
		try {
			String[] headers = { "ID", "Date", "Issued By", "Amount"};
			String[][] entries = {{"1", "June 20, 2012", "John David S. Baltazar", "2500"}, {"2", "January 18, 2012",  "Juan dela Cruz", "5000"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.AR_PAYMENTS), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillAR(){
		try {
			String[] headers = { "ID", "Date", "Issued By", "Receiver", "Balance"};
			String[][] entries = {{"1", "June 20, 2013", "John David S. Baltazar", "Juan dela Cruz", "500"}, {"2", "June 18, 2013", "Juan dela Cruz", "John David S. Baltazar", "5000"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.ACCOUNT_RECEIVABLES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillPullout(){
		try {
			String[] headers = { "ID", "Date", "Issued By", "Product", "Remarks"};
			String[][] entries = {{"1", "May 20, 2013", "John David S. Baltazar", "Hog Feeds", "expired"}, {"2", "April 18, 2013", "Juan dela Cruz", "Chicken Feeds", ""}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.PULLOUT), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillCashAdvance() {
		try {
			String[] headers = { "ID", "Date", "Issued by", "Issued For", "Amount" };
			String[][] entries = {{"1", "June 20, 2013", "John David S. Baltazar", "Juan dela Cruz", "500"}, {"2", "June 18, 2013", "Juan dela Cruz", "John David S. Baltazar", "5000"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.CASH_ADVANCE), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillCAPayments() {
		try {
			String[] headers = { "ID", "Date", "Issued by", "Amount" };
			String[][] entries = {{"1", "June 20, 2011", "John David S. Baltazar", "500"}, {"2", "December 18, 2010", "Juan dela Cruz", "5000"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.CA_PAYMENTS), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillStockPurchase() {

		try {

			String[] headers = { "Puchase No", "Date", "Supplier" };
			String[][] entries = {{"1", "June 20, 2013", "Universal Feeds Corp."}, {"2", "June 18, 2013", "Rubina Foods Inc."}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), "STOCK PURCHASE"), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void fillDelivery() {
		try {
			String[] headers = { "ID", "Date", "Received by", "Remarks"};
			String[][] entries = {{"1", "April 18, 2013", "John David S. Baltazar", ""}, {"2", "May 11, 2012", "John David S. Baltazar", "Hoax"}};
//			String[][] entries = new String[5][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.DELIVERY), BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillSuppliers() {
		try {
			String[] headers = { "ID", "Name", "Address", "Contact No.","Contact Person" };
//			String[][] entries = new String[5][headers.length];
			String[][] entries = {{"1", "Rubina Hog Feeds Inc.", "Marikina City", "", ""}, {"2", "Universal Feeds Corp.", "Tacloban City", "325-5689", "Pugad Baboy"}};

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.SUPPLIERS), BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillSalesOrder() {
		try {

			String[] headers = { "Sales No.", "Date", "Cashier" };
			String[][] entries = {{"1", "June 8, 2013", "Juan dela Cruz"}, {"2", "May 27, 2013", "Juan dela Cruz"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), "SALES ORDER"), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillProfiles() {
		try {
			String[] headers = { "ID", "Last Name", "First Name", "Middle Name", "Contact No." };
			String[][] entries = {{"1", "Baltazar", "John David", "S", "09161429583"}, {"2", "dela Cruz", "Juan", "B", "09161234567"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.PROFILES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillEmployees() {
		try {
			String[] headers = { "ID", "Name", "Designation", "Status", "Remarks" };
//			String[][] entries = new String[1][headers.length];
			String[][] entries = {{"1", "John David Baltazar", "Janitor", "Active", ""}, {"2", "Juan dela Cruz", "Sales Clerk", "Terminated", ""}};

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.EMPLOYEES), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void fillAccounts() {
		try {
			String[] headers = { "Account No", "Username", "Account Type", "Name", "Contact No" };
			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.ACCOUNTS), BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillProducts() {

		try {
			String[] headers = { "Item No.", "Name", "Quantity (kg)", "Quantity (sack)", "Display (kg)", "Display (sack)"};
//			String[][] entries = new String[1][headers.length];
			
			String[][] entries = {{"1", "Hog Feeds", "25", "5", "15", "5"}, {"2", "Chicken Feeds", "30", "10", "5", "3"}};

			add(new TableUtilPanel(new TablePanel(entries, headers, null), Tables.PRODUCTS), BorderLayout.CENTER);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void fillLogs() {

		try {
			String[] headers = { "Date", "Description" };
			String[][] entries = {{"June 21, 2013", "dummy_acct logged in"}};
//			String[][] entries = new String[1][headers.length];

			add(new TableUtilPanel(new TablePanel(entries, headers, null), "LOGS"), BorderLayout.CENTER);
			
			Values.tablePanel.getSoyTable().getColumnModel().getColumn(0).setMinWidth(200);
			Values.tablePanel.getSoyTable().getColumnModel().getColumn(0).setMaxWidth(200);
		
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
