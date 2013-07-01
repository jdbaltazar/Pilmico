package core.test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import common.entity.log.Log;
import common.entity.log.LogType;
import common.entity.note.Note;
import common.entity.note.NoteType;
import common.entity.product.Category;
import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.entity.profile.Employee;
import common.entity.salary.CashAdvance;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;
import common.entity.supplier.Supplier;
import common.manager.AccountManager;
import common.manager.CashAdvanceManager;
import common.manager.EmployeeManager;
import common.manager.ProductManager;
import common.manager.LogManager;
import common.manager.Manager;
import common.manager.NoteManager;
import common.manager.SalesManager;
import common.manager.StoreManager;
import common.manager.SupplierManager;
import core.persist.AccountPersistor;
import core.persist.CashAdvancePersistor;
import core.persist.EmployeePersistor;
import core.persist.ProductPersistor;
import core.persist.LogPersistor;
import core.persist.NotePersistor;
import core.persist.SalesPersistor;
import core.persist.StorePersistor;
import core.persist.SupplierPersistor;
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

	public static void main(String[] args) {

		String pass = "1234567890qwertyuiop";
		try {
			String encrypted = SecurityTool.getInstance().getEncryptStringValue(pass);

			System.out.println("Encrypted: " + encrypted);

			System.out.println("Decrypted: " + SecurityTool.getInstance().decrypt(encrypted.getBytes()));
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
