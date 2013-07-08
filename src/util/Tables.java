package util;

public class Tables {

	public static String PRODUCTS = "PRODUCTS";
	public static String PULLOUTS = "PULLOUTS";
	
	public static String ACCOUNT_RECEIVABLES = "ACCOUNT RECEIVABLES";
	public static String AR_PAYMENTS = "AR PAYMENTS";

	public static String CASH_ADVANCE = "CASH ADVANCE";
	public static String CA_PAYMENTS = "CA PAYMENTS";
	
	
	public static String PROFILES = "PROFILES";
	public static String ACCOUNTS = "ACCOUNTS";
	public static String EMPLOYEES = "EMPLOYEES";

	public static String DELIVERIES = "DELIVERIES";
	public static String SUPPLIERS = "SUPPLIERS";

	public static String EXPENSES = "EXPENSES";
	public static String SALARY = "SALARY";
	
	public static String SALES = "SALES";
	
	public static String DISCOUNTS = "DISCOUNTS";
	
	public static String DEPOSITS = "DEPOSITS";

	public static String INVENTORY_SHEET = "INVENTORY SHEET";

	public static String LOGS = "LOGS";

	public static String[] productFormLabel = { "Name*", "Description",
			"Available?*","kilos per sack", "Quantity (in sack)*",
			"Quantity (in kg)*", "Display (in sack)*",
			"Display (in kg)*", "Price (per sack)*", "Price (per kg)*", "Category*", "Alert using: *",
			"Alert on Quantity*" };

	public static String[] profileFormLabel = { "Last Name*", "First Name*",
		"Middle Name", "Address", "Contact Number", "Customer" };
	
	public static String[] accountFormLabel = { "Employee*", "Account Type*",
			"Username*", "Password*" };

	public static String[] employeeFormLabel = { "Profile*", "Designation*",
			"Employment Status*", "Starting Date", "Salary", "Remarks", "End Date" };

	public static String[] supplierFormLabel = { "Name*", "Address", "TIN",
			"Contact No.", "Contact Person", "Remarks" };
	
	public static String[] cashAdvanceFormLabel = {"Date*", "Issued By*", "Issued For*", "Amount*", "Balance*"};

	
/*	import java.awt.*;

	public class GetMouse
	{
		public static void main(String[] args)
		{
			while (true)
			{
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				int x = (int) b.getX();
				int y = (int) b.getY();
				System.out.println(x + ":" + y);
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}*/
}
