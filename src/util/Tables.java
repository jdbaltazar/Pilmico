package util;

public class Tables {

	public static String PRODUCTS = "PRODUCTS";
	public static String PULLOUT = "PULLOUT";
	
	public static String ACCOUNT_RECEIVABLES = "ACCOUNT RECEIVABLES";
	public static String AR_PAYMENTS = "AR PAYMENTS";

	public static String CASH_ADVANCE = "CASH ADVANCE";
	public static String CA_PAYMENTS = "CA PAYMENTS";
	
	public static String PROFILES = "PROFILES";
	public static String ACCOUNTS = "ACCOUNTS";
	public static String EMPLOYEES = "EMPLOYEES";

	public static String DELIVERY = "DELIVERY";
	public static String SUPPLIERS = "SUPPLIERS";

	public static String EXPENSES = "EXPENSES";
	public static String SALES = "SALES";

	public static String SUPER_FORM = "SUPER FORM";

	public static String LOGS = "LOGS";

	public static String[] productFormLabel = { "Name*", "Description",
			"Available?*", "Display (in kilo)*",
			"Display (in sack)*", "Quantity (in kg)*",
			"Quantity (in sack)*", "Category*", "Allow Alert?*",
			"Alert on Quantity*" };

	public static String[] profileFormLabel = { "Last Name*", "First Name*",
		"Middle Name", "Address", "Contact Number" };
	
	public static String[] accountFormLabel = { "Employee*", "Account Type*",
			"Username*", "Password*" };

	public static String[] employeeFormLabel = { "Profile*", "Designation*",
			"Employment Status*", "Starting Date", "Salary", "Remarks", "End Date" };

	public static String[] supplierFormLabel = { "Name*", "Address", "TIN",
			"Contact No.", "Contact Person", "Remarks" };
	
	public static String[] cashAdvanceFormLabel = {"Date*", "Issued By*", "Issued For*", "Amount*", "Remarks", "Paid*"};

}
