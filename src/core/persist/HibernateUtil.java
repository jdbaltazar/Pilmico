package core.persist;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import common.entity.cashadvance.CashAdvance;
import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.expense.Expense;
import common.entity.expense.ExpenseDetail;
import common.entity.expense.ExpenseType;
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
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
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

			// entities
			
			conf.addAnnotatedClass(Product.class);
			conf.addAnnotatedClass(Price.class);
			conf.addAnnotatedClass(Category.class);
			
			
			// conf.addAnnotatedClass(LogType.class);
			// conf.addAnnotatedClass(Log.class);
			// conf.addAnnotatedClass(AccountType.class);
			// conf.addAnnotatedClass(Account.class);
			// conf.addAnnotatedClass(Employee.class);
			// conf.addAnnotatedClass(Supplier.class);
			// conf.addAnnotatedClass(Store.class);
			// conf.addAnnotatedClass(NoteType.class);
			// conf.addAnnotatedClass(Note.class);
			//
			// conf.addAnnotatedClass(DeliveryDetail.class);
			// conf.addAnnotatedClass(Delivery.class);
			// conf.addAnnotatedClass(Sales.class);
			// conf.addAnnotatedClass(SalesDetail.class);
			// conf.addAnnotatedClass(ExpenseType.class);
			// conf.addAnnotatedClass(Expense.class);
			// conf.addAnnotatedClass(ExpenseDetail.class);
			// conf.addAnnotatedClass(ExpenseType.class);
			// conf.addAnnotatedClass(CashAdvance.class);
			// conf.addAnnotatedClass(PullOut.class);
			// conf.addAnnotatedClass(PullOutDetail.class);

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
