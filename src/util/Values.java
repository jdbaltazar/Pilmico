package util;

import gui.CenterPanel;
import gui.FooterPanel;
import gui.MainFrame;
import gui.TopPanel;
import gui.forms.add.AccountForm;
import gui.forms.add.ProductForm;
import gui.forms.add.SalesOrderForm;
import gui.forms.add.StockPurchasePanel;
import gui.forms.edit.EditSalesOrder;
import gui.forms.edit.EditStockPurchase;
import gui.list.AccountTypeList;
import gui.list.CategoryList;
import gui.list.ConditionList;
import gui.list.NoteTypeList;
import gui.list.UnitList;
import gui.panels.LeftPanel;
import gui.panels.MenuPanel;
import gui.panels.TablePanel;
import gui.panels.TableUtilPanel;
import gui.panels.extra.AddEntryPanel;
import gui.panels.extra.EditPanel;
import gui.popup.NotesPopup;

import java.awt.Color;
import java.awt.Toolkit;

public class Values {

	public static CenterPanel centerPanel;
	public static TablePanel tablePanel;
	public static TopPanel topPanel;
	public static FooterPanel footerPanel;
	public static EditPanel editPanel;
	public static LeftPanel leftPanel;
	public static MainFrame mainFrame;
	public static AddEntryPanel addEntryPanel;
	public static TableUtilPanel tableUtilPanel;
	public static StockPurchasePanel stockPurchasePanel;
	public static SalesOrderForm salesOrderForm;
	public static NotesPopup notesPopup;
	public static MenuPanel menuPanel;
	public static AccountTypeList accountTypeList;
	public static NoteTypeList noteTypeList;
	public static UnitList unitList;
	public static CategoryList categoryList;
	public static ConditionList conditionList;
	public static ProductForm itemForm;
	public static AccountForm accountForm;
	public static EditStockPurchase editStockPurchase;
	public static EditSalesOrder editSalesOrder;

	public static int WIDTH = 800;
	public static int HEIGHT = 600;

	public static final int HOME = -1;
	
	public static final int PRODUCTS = 0;
	public static final int PULLOUT = 1;
	public static final int ACCOUNT_RECEIVABLES = 2;
	public static final int AR_PAYMENTS = 3;
	public static final int DELIVERY = 4;
	public static final int SUPPLIERS = 5;
	public static final int CASH_ADVANCE = 6;
	public static final int CA_PAYMENTS = 7;
	public static final int PROFILES = 8;
	public static final int EMPLOYEES = 9;
	public static final int ACCOUNTS = 10;
	public static final int EXPENSES = 11;
	public static final int SALES = 12;
	public static final int SUPER_FORM = 13;
	public static final int LOGS = 14;

	public static final int UNITS = 0;
	public static final int CONDITION = 1;
	public static final int CATEGORY = 2;
	public static final int ACCOUNT_TYPE = 3;
	public static final int LOG_TYPE = 4;
	public static final int NOTE_TYPE = 5;

	public static final int ADD = 0;
	public static final int EDIT = 1;

	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit()
			.getScreenSize().width;
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit()
			.getScreenSize().height;

	// public static Color gradient1 = new Color(249,255,249);
	// public static Color gradient2 = new Color(240,255,240);

	public static Color gradient1 = new Color(245, 245, 253);
	public static Color gradient2 = new Color(230, 230, 250);

	public static String[] productFormLabel = { "Name*", "Description",
			"For Sale?*", "Unit*", "Selling Price (per kilo)*",
			"Selling Price (per sack)*", "Quantity (in kg)*",
			"Display (in kg)*", "Category*", "Allow Alert?*",
			"Alert on Quantity*" };

	public static String[] accountFormLabel = { "Employee*","Account Type*","Username*", "Password*"};
	
	public static String[] employeeFormLabel = { "Last Name*", "First Name*",
		"Middle Name", "Designation*",
		"Address", "Contact No.", };

	public static String[] supplierFormLabel = { "Name*", "Address", "TIN", "Contact No.",
			"Contact Person", "Remarks" };
	
	public static String[] stockHeader = { "Stock Number", "Name", "Category",
			"Brand", "Selling Price", " " };

	public static String[] stockPurchaseHeader = { "Stock Purchased",
			"Quantity", "Purchase Price", "Date Purchased", };

}
