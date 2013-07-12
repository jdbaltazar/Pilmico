package core.persist;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.Credentials;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;
import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.inventorysheet.Breakdown;
import common.entity.inventorysheet.BreakdownLine;
import common.entity.inventorysheet.Denomination;
import common.entity.inventorysheet.InventorySheetData;
import common.entity.inventorysheet.InventorySheetDataDetail;
import common.entity.log.Log;
import common.entity.log.LogType;
import common.entity.note.Note;
import common.entity.note.NoteType;
import common.entity.product.Category;
import common.entity.product.Price;
import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.entity.profile.Designation;
import common.entity.profile.Employee;
import common.entity.profile.EmploymentStatus;
import common.entity.profile.Person;
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
import common.entity.salary.Fee;
import common.entity.salary.FeeDeduction;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;
import common.entity.store.Store;
import common.entity.supplier.Supplier;
import core.security.SecurityTool;

public class HibernateUtil {

	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/";
	private static SessionFactory sessionFactory;

	static {
		init();
	}

	public static void init() {

		/*
		 * <<<<<<< HEAD // mysql account credectials if
		 * (!tryToBuildSessionFactory("root", "")) =======
		 */
		// TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		if (!tryToBuildSessionFactory()) {
			throw new RuntimeException("connection unsuccessful");
		} else {
			System.out.println("Connection successful");
		}

	}

	private static boolean tryToBuildSessionFactory() throws ExceptionInInitializerError {
		try {

			Properties p = new Properties();
			p.setProperty("hibernate.connection.driver_class", DRIVER_NAME);

			p.setProperty("hibernate.connection.url", URL + Credentials.getInstance().getDatabaseName());
			p.setProperty("hibernate.show_sql", "false");
			p.setProperty("hibernate.connection.username", SecurityTool.decrypt(Credentials.getInstance().getUsername()));
			p.setProperty("hibernate.connection.password", SecurityTool.decrypt(Credentials.getInstance().getPassword()));

			p.setProperty("log4j.rootLogger", "ERROR, myConsoleAppender");
			p.setProperty("log4j.appender.myConsoleAppender", "org.apache.log4j.ConsoleAppender");
			p.setProperty("log4j.appender.myConsoleAppender.layout", "org.apache.log4j.PatternLayout");
			p.setProperty("log4j.appender.myConsoleAppender.layout.ConversionPattern", "%-5p %c %x - %m%n");

			PropertyConfigurator.configure(p);

			Configuration conf = new Configuration();
			conf.setProperties(p);

			conf.addAnnotatedClass(LogType.class);
			conf.addAnnotatedClass(Log.class);

			conf.addAnnotatedClass(Designation.class);
			conf.addAnnotatedClass(Person.class);
			conf.addAnnotatedClass(EmploymentStatus.class);
			conf.addAnnotatedClass(Employee.class);
			conf.addAnnotatedClass(AccountType.class);
			conf.addAnnotatedClass(Account.class);

			conf.addAnnotatedClass(NoteType.class);
			conf.addAnnotatedClass(Note.class);

			conf.addAnnotatedClass(Product.class);
			conf.addAnnotatedClass(Price.class);
			conf.addAnnotatedClass(Category.class);

			conf.addAnnotatedClass(Store.class);
			conf.addAnnotatedClass(Supplier.class);

			conf.addAnnotatedClass(DeliveryDetail.class);
			conf.addAnnotatedClass(Delivery.class);
			conf.addAnnotatedClass(SalesDetail.class);
			conf.addAnnotatedClass(Sales.class);
			conf.addAnnotatedClass(Expense.class);
			conf.addAnnotatedClass(Person.class);
			conf.addAnnotatedClass(DailyExpensesType.class);
			conf.addAnnotatedClass(DailyExpensesDetail.class);
			conf.addAnnotatedClass(DailyExpenses.class);

			conf.addAnnotatedClass(ARPayment.class);
			conf.addAnnotatedClass(AccountReceivableDetail.class);
			conf.addAnnotatedClass(AccountReceivable.class);

			conf.addAnnotatedClass(PullOutDetail.class);
			conf.addAnnotatedClass(PullOut.class);

			conf.addAnnotatedClass(Fee.class);
			conf.addAnnotatedClass(FeeDeduction.class);
			conf.addAnnotatedClass(CAPayment.class);
			conf.addAnnotatedClass(CashAdvance.class);
			conf.addAnnotatedClass(SalaryRelease.class);

			conf.addAnnotatedClass(Bank.class);
			conf.addAnnotatedClass(BankAccount.class);
			conf.addAnnotatedClass(Deposit.class);

			conf.addAnnotatedClass(Denomination.class);
			conf.addAnnotatedClass(BreakdownLine.class);
			conf.addAnnotatedClass(Breakdown.class);

			conf.addAnnotatedClass(DiscountIssue.class);

			conf.addAnnotatedClass(InventorySheetDataDetail.class);
			conf.addAnnotatedClass(InventorySheetData.class);

			sessionFactory = conf.buildSessionFactory();

			return true;
		} catch (Throwable ex) {
			ex.printStackTrace();
			sessionFactory = null;
			return false;
		}
	}

	public static Session startSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static void endSession() {
		sessionFactory.close();
	}

	public static boolean isConnected() {
		return sessionFactory != null;
	}

	public static boolean evaluate() {
		if (!isConnected())
			return tryToBuildSessionFactory();
		else
			return false;
	}

	public static void close() {
		if (isConnected()) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}
}
