package core.persist;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;
import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.entity.deposit.Deposit;
import common.entity.inventorysheet.Breakdown;
import common.entity.inventorysheet.BreakdownLine;
import common.entity.inventorysheet.Denomination;
import common.entity.inventorysheet.InventorySheet;
import common.entity.inventorysheet.InventorySheetDetail;
import common.entity.log.Log;
import common.entity.log.LogType;
import common.entity.note.Note;
import common.entity.note.NoteType;
import common.entity.product.Category;
import common.entity.product.Price;
import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.entity.profile.Employee;
import common.entity.profile.EmploymentStatus;
import common.entity.profile.Person;
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
import common.entity.salary.CADeduction;
import common.entity.salary.CashAdvance;
import common.entity.salary.Fee;
import common.entity.salary.FeeDeduction;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;
import common.entity.store.Store;
import common.entity.supplier.Supplier;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		init();
	}

	public static void init() {
		// TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		// mysql account credectials
		if (!tryToBuildSessionFactory("root", ""))
			throw new RuntimeException("connection unsuccessful");
	}

	private static boolean tryToBuildSessionFactory(String username, String password) throws ExceptionInInitializerError {
		try {
			Properties p = new Properties();
			p.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

			// soybean-database name
			p.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/pilmico");
			p.setProperty("hibernate.show_sql", "false");
			p.setProperty("hibernate.connection.username", username);
			p.setProperty("hibernate.connection.password", password);
			// p.setProperty("hibernate.search.default.indexBase",
			// "./lucene-index");
			// p.setProperty("hibernate.search.default.directory_provider",
			// "filesystem");

			Configuration conf = new Configuration();
			conf.setProperties(p);

			conf.addAnnotatedClass(LogType.class);
			conf.addAnnotatedClass(Log.class);

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
			conf.addAnnotatedClass(CADeduction.class);
			conf.addAnnotatedClass(CashAdvance.class);
			conf.addAnnotatedClass(SalaryRelease.class);

			conf.addAnnotatedClass(Bank.class);
			conf.addAnnotatedClass(BankAccount.class);
			conf.addAnnotatedClass(Deposit.class);

			conf.addAnnotatedClass(Denomination.class);
			conf.addAnnotatedClass(BreakdownLine.class);
			conf.addAnnotatedClass(Breakdown.class);

			conf.addAnnotatedClass(InventorySheetDetail.class);
			conf.addAnnotatedClass(InventorySheet.class);

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

	public static boolean evaluate(String username, String password) {
		if (!isConnected())
			return tryToBuildSessionFactory(username, password);
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
