package core.test;

import gui.forms.util.DateTool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import util.DateFormatter;
import util.Utility;

import common.entity.inventorysheet.InventorySheetData;
import common.manager.Manager;

import app.Credentials;
import core.database.DatabaseTool;
import core.security.SecurityTool;

public class Test {

	public boolean tbBackup(String dbName, String dbUserName, String dbPassword, String path) {

		String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
		Process runtimeProcess;
		try {
			System.out.println(executeCmd);// this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
				return true;
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void runSql(String pSql) throws FileNotFoundException, Exception {

		String username = SecurityTool.decryptString(Credentials.getInstance().getUsername());
		String password = SecurityTool.decryptString(Credentials.getInstance().getPassword());
		String tCommand = "mysql -u " + username + (password != null ? " -p" + password : "") + " " + "pilmico";
		System.out.println(tCommand);

		Process tProcess = Runtime.getRuntime().exec(tCommand);
		OutputStream tOutputStream = tProcess.getOutputStream();
		Writer w = new OutputStreamWriter(tOutputStream);
		System.out.println(pSql);
		w.write(pSql);
		w.flush();

		Scanner in = new Scanner(tProcess.getErrorStream());

		String errorMessage = "";

		while (in.hasNext()) {
			errorMessage += in.next() + " ";
		}

		if (errorMessage.length() > 0) {
			System.out.println(errorMessage);
			// throw new ClientSqlExecutionException(errorMessage);
		}

	}

	public static void main(String[] args) {

		// Date d = new Date();
		//
		// System.out.println("Today: " +
		// DateFormatter.getInstance().getFormat(Utility.CompleteFormat).format(DateTool.getDateWithoutTime(d)));
		// System.out.println("Tom: " +
		// DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(DateTool.getTomorrowDate(d)));

		try {
			System.out.println("valid deliveries for today: " + Manager.deliveryManager.getValidDeliveriesOn(new Date()).size());
			System.out.println("valid deliveries: " + Manager.deliveryManager.getValidDeliveries().size());
			System.out.println("invalid deliveries for today: " + Manager.deliveryManager.getInvalidDeliveriesOn(new Date()).size());
			System.out.println("invalid deliveries: " + Manager.deliveryManager.getInvalidDeliveries().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// InventorySheetData isd;
		// try {
		//
		// Date d = DateTool.getDateWithoutTime(new Date());
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(d);
		// // Set time fields to zero
		// cal.set(Calendar.HOUR_OF_DAY, 0);
		// cal.set(Calendar.MINUTE, 0);
		// cal.set(Calendar.SECOND, 0);
		// cal.set(Calendar.MILLISECOND, 0);
		// // Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2013);
		// cal.set(Calendar.MONTH, Calendar.JULY);
		// cal.set(Calendar.DAY_OF_MONTH, 31);
		// d = cal.getTime();
		// // Set time fields to zero
		//
		// System.out.println(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(d));
		//
		// if (Manager.inventorySheetDataManager.isValidFor(d))
		// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// DatabaseTool.decryptAndUpdate("root", "123456", "pilmico",
		// "pilmico-create.sql");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		// DatabaseTool.backup(dbUserName, dbPassword, dbName, path);

		// try {
		// //
		// SecurityTool.writeToFile(SecurityTool.encryptFile("data/pilmico-create.sql"),
		// // new FileOutputStream("data/unenc2.txt"));
		// SecurityTool.writeToFile(SecurityTool.decryptFile("data/ok.txt"),
		// "data/ok2.txt");
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// List<Expense> expenses = Manager.dailyExpenseManager.getExpenses();
		//
		// System.out.println("size: " + expenses.size());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String hello = "pilmico";
		//
		// try {
		// System.out.println("dec: " +
		// SecurityTool.decrypt("htre1/TF0v8SgQzHAHkARw=="));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// Database.resetDatabase();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// String pass = "paasword";
		// try {
		// String encrypted = "@ðÞNx£õê‚0]Ž¥KÅÉ";
		// //SecurityTool.getInstance().getEncryptStringValue(pass);
		//
		// System.out.println("Encrypted: " + encrypted);
		//
		// System.out.println("Decrypted: " +
		// SecurityTool.getInstance().decrypt(encrypted.getBytes()));
		// } catch (InvalidKeyException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (BadPaddingException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (IllegalBlockSizeException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// Configuration config = new Configuration("username", "password",
		// "urlconnection");
		// XStream xStream = new XStream(new DomDriver());
		// xStream.alias("configuration", Configuration.class);
		// // System.out.println(xStream.toXML(config));
		//
		// // /Write to a file in the file system
		// try {
		//
		// File file = new File("D:/hello.xml");
		// FileOutputStream fs = new FileOutputStream(file);
		// xStream.toXML(config, fs);
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// }

		// XStream xs = new XStream(new DomDriver());
		// Configuration config = new Configuration();
		//
		// try {
		// FileInputStream fis = new FileInputStream("config.xml");
		// xs.fromXML(fis, config);
		//
		// // print the data from the object that has been read
		// System.out.println("connection: " +
		// config.getHibernateConnectionUrl());
		//
		// } catch (FileNotFoundException ex) {
		// ex.printStackTrace();
		// }

		// File file = new File("config.xml");
		// Configuration config = (Configuration)xs.fromXML(file);
		// System.out.println("connection: " +
		// config.getHibernateConnectionUrl());

		// ================================================================================

		// XStream xs = new XStream(new DomDriver());
		// xs.alias("configuration", Credentials.class);
		// Credentials c = new Credentials();
		//
		// try {
		// FileInputStream fis = new FileInputStream("config.xml");
		// xs.fromXML(fis, c);
		//
		// // print the data from the object that has been read
		// System.out.println("connection: " + c.getHibernateConnectionUrl());
		//
		// } catch (FileNotFoundException ex) {
		// ex.printStackTrace();
		// }

		// String dbUserName = "root";
		// String dbPassword = "123456";
		// String dbName = "pilmico";
		// String path = "D://backup2.sql";
		//
		// String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword +
		// " --add-drop-database -B " + dbName + " -r " + path;
		// Process runtimeProcess;
		// try {
		// System.out.println(executeCmd);// this out put works in mysql shell
		// runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		// int processComplete = runtimeProcess.waitFor();
		//
		// if (processComplete == 0) {
		// System.out.println("Backup created successfully");
		// } else {
		// System.out.println("Could not create the backup");
		// }
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }

		// public static boolean restoreDB(String dbName, String dbUserName,
		// String dbPassword, String source) {
		//
		// String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName,
		// "--password=" + dbPassword, dbName,"-e", "source "+source};
		//
		// Process runtimeProcess;
		// try {
		//
		// runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		// int processComplete = runtimeProcess.waitFor();
		//
		// if (processComplete == 0) {
		// System.out.println("Backup restored successfully");
		// return true;
		// } else {
		// System.out.println("Could not restore the backup");
		// }
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		//
		// return false;
		// }

		// String[] executeCmd = new String[] { "/bin/sh", "-c", "mysql -u" +
		// dbUser + " -p" + dbPass + " " + dbName + " < D://backup.sql" };
		// Process runtimeProcess;
		// try {
		// runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		// int processComplete = runtimeProcess.waitFor();
		//
		// if (processComplete == 0) {
		// System.out.println("success");
		// } else {
		// System.out.println("restore failure");
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {

		// Account a = new Account("aaa", "aaaa", new
		// AccountType(AccountType.employee), "aaa", "aaa", "aaa");
		// AccountManager am = new AccountPersistor();
		// am.addAccount(a);

		// Item item = new Item("aaaa", true, new Unit("pcs"), 0.00, 0.00, 120,
		// new ItemCondition("Good Condition"), new Category("Beverage"), true,
		// 0);
		// ItemManager im = new ItemPersistor();
		// im.addItem(item);

		// Supplier s = new Supplier("aaaa", "aaaaaaa");
		// SupplierManager sm = new SupplierPersistor();
		// sm.addSupplier(s);

		// Note n = new Note(new Date(), "aaaaaaaa", "aaaaaaa", new
		// NoteType("Important"));
		//
		// NoteManager nm = new NotePersistor();
		//
		// nm.addNote(n);

		// Log log = new Log(new Date(), new LogType(LogType.SYSTEM),
		// "asasas");
		// LogManager lm = new LogPersistor();
		//
		// lm.addLog(log);

		// StoreInfo si = new StoreInfo("UUUUUUUUUUU", "oooooooooooo");
		// StoreManager sm = new StorePersistor();
		// sm.updateStoreInfo(si);

		// Account a = new Account("aaaaaaaaaaaa", "aaaaaaaaaaaa", new
		// AccountType(AccountType.employee), "aaaaaaaaaaaa", "aaaaaaaaaaaa",
		// "aaaaaaaaaaaa");
		// AccountManager am = new AccountPersistor();
		//
		// am.addAccount(a);

		// ItemManager im = new ItemPersistor();
		// Item i = im.getItem(1);
		// SalesOrder so = new SalesOrder(new Date(), a);
		// so.addSalesOrderDetail(new SalesOrderDetail(so, 12, i));
		// SalesOrderManager som = new SalesOrderPersistor();
		// som.addSalesOrder(so);

		// List<Product> items = Manager.itemManager.searchItemColumns("k");
		//
		// System.out.println("size: " + items.size());

		// CashAdvance c = new CashAdvance(new Date(), false, 100, new
		// Employee("a", "a", "a"), new Account("a", "a", new AccountType(
		// AccountType.manager), new Employee("a", "a", "a")), true);
		//
		// CashAdvanceManager cam = new CashAdvancePersistor();
		// cam.addCashAdvance(c);

		// Employee e = new Employee("a", "a", "designation");
		// EmployeeManager emp = new EmployeePersistor();
		//
		// emp.addEmployee(e);

		// try {
		// List<Product> products =
		// Manager.getInstance().productManager.getProducts();
		//
		// for (Product p : products) {
		// System.out.println(p);
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
