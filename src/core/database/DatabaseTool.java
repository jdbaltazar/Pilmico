package core.database;

import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;

import app.AppSettings;
import app.Credentials;
import app.DatabaseSettings;

import util.Values;

import core.persist.HibernateUtil;
import core.security.SecurityTool;

public class DatabaseTool {

	public static final String RESET_FILE = "data/pilmico-create.sql";
	public static final String TEMP_FILE = "temp/temp.tmp";
	public static Process runtimeProcess;
	private static UtilityPopup uP;
	private static DatabaseTool ins;
	private static String dbUserName, dbPassword, dbName, filePath;

	public static DatabaseTool getInstance() {
		if (ins == null)
			ins = new DatabaseTool();
		return ins;
	}

	static {
		try {
			Class.forName(HibernateUtil.DRIVER_NAME).newInstance();
			System.out.println("*** Driver loaded");
		} catch (Exception e) {
			System.out.println("*** Error : " + e.toString());
			System.out.println("*** ");
			System.out.println("*** Error : ");
			e.printStackTrace();
		}

	}

	public static Connection getConnection(String userName, String password, String dbName) throws SQLException {
		return DriverManager.getConnection(HibernateUtil.URL + dbName, userName, password);
	}

	// public static boolean backup(String dbUserName, String dbPassword, String
	// dbName, String path, UtilityPopup uP) {
	//
	// HibernateUtil.endSession();
	// DatabaseTool.uP = uP;
	//
	// filePath = path;
	// String executeCmd = "mysqldump -u " + dbUserName;
	// if (!dbPassword.equals(""))
	// executeCmd = executeCmd + " -p" + dbPassword;
	// executeCmd = executeCmd + " --add-drop-database -B " + dbName + " -r " +
	// path;
	//
	// try {
	// System.out.println(executeCmd);// this out put works in mysql shell
	// runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	//
	// // int processComplete = 1;
	// Thread thread = new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// boolean isRunning = true;
	//
	// while (isRunning) {
	// try {
	// int processComplete = runtimeProcess.waitFor();
	//
	// if (processComplete == 0) {
	// try {
	//
	// String s = new String();
	// StringBuffer sb = new StringBuffer();
	//
	// FileReader fr = new FileReader(filePath);
	// BufferedReader br = new BufferedReader(fr);
	//
	// while ((s = br.readLine()) != null) {
	// sb.append(SecurityTool.encryptString(s) + "\n");
	// }
	// br.close();
	//
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if (DatabaseTool.uP != null)
	// DatabaseTool.uP.dispose();
	// new SuccessPopup("DB Backup").setVisible(true);
	// Values.topPanel.closeBalloonPanel();
	// isRunning = false;
	//
	// } else {
	// if (DatabaseTool.uP != null)
	// DatabaseTool.uP.dispose();
	// JOptionPane.showMessageDialog(Values.mainFrame,
	// "Could not create the backup", "Error", JOptionPane.ERROR_MESSAGE);
	// isRunning = false;
	// }
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// });
	//
	// thread.start();
	// if (uP != null)
	// uP.showProgressBar();
	//
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// HibernateUtil.startSession();
	// return false;
	// }

	public static boolean encryptedBackup(String dbUserName, String dbPassword, String dbName, String path, UtilityPopup uP) {

		HibernateUtil.endSession();
		DatabaseTool.uP = uP;

		filePath = path;
		String executeCmd = "mysqldump -u " + dbUserName;
		if (!dbPassword.equals(""))
			executeCmd = executeCmd + " -p" + dbPassword;
		executeCmd = executeCmd + " --add-drop-database -B " + dbName + " -r " + TEMP_FILE;

		try {
			System.out.println(executeCmd);// this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);

			// int processComplete = 1;
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					boolean isRunning = true;

					while (isRunning) {
						try {
							int processComplete = runtimeProcess.waitFor();

							if (processComplete == 0) {
								try {
									File original = new File(TEMP_FILE);
									File backUp = new File(filePath);
									SecurityTool.encryptFile(original, backUp);
									original.delete();

								} catch (Exception e) {
									e.printStackTrace();
								}

								if (DatabaseTool.uP != null)
									DatabaseTool.uP.dispose();
								new SuccessPopup("DB Backup").setVisible(true);
								Values.topPanel.closeBalloonPanel();
								isRunning = false;

							} else {
								if (DatabaseTool.uP != null)
									DatabaseTool.uP.dispose();
								JOptionPane.showMessageDialog(Values.mainFrame, "Could not create the backup", "Error", JOptionPane.ERROR_MESSAGE);
								isRunning = false;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});

			thread.start();
			if (uP != null)
				uP.showProgressBar();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		HibernateUtil.startSession();
		return false;
	}

	// public static boolean backup(String dbUserName, String dbPassword, String
	// dbName, String path) {
	//
	// HibernateUtil.endSession();
	//
	// filePath = path;
	//
	// System.out.println("path: " + filePath);
	// String executeCmd = "mysqldump -u " + dbUserName;
	// if (!dbPassword.equals(""))
	// executeCmd = executeCmd + " -p" + dbPassword;
	// executeCmd = executeCmd + " --add-drop-database -B " + dbName + " -r " +
	// path;
	//
	// System.out.println("cmd: " + executeCmd);
	//
	// Process runtimeProcess;
	// try {
	// System.out.println(executeCmd);// this output works in mysql shell
	// runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	//
	// int processComplete = runtimeProcess.waitFor();
	// System.out.println("process complete: " + processComplete);
	//
	// if (processComplete == 0) {
	// System.out.println("Backup created successfully");
	// return true;
	// } else {
	// System.out.println("Could not create the backup");
	// }
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// HibernateUtil.startSession();
	// return false;
	// }

	// public static void update(String dbUserName, String dbPassword, String
	// dbName, File file) throws SQLException {
	//
	// HibernateUtil.endSession();
	//
	// String s = new String();
	// StringBuffer sb = new StringBuffer();
	//
	// try {
	// FileReader fr = new FileReader(file);
	// BufferedReader br = new BufferedReader(fr);
	//
	// while ((s = br.readLine()) != null) {
	// sb.append(s + "\n");
	// }
	// br.close();
	//
	// // here is our splitter ! We use ";" as a delimiter for each request
	// // then we are sure to have well formed statements
	// String[] inst = sb.toString().split(";");
	//
	// Connection c = DatabaseTool.getConnection(dbUserName, dbPassword, dbName);
	// Statement st = c.createStatement();
	//
	// for (int i = 0; i < inst.length; i++) {
	// // we ensure that there is no spaces before or after the request
	// // string
	// // in order to not execute empty statements
	// if (!inst[i].trim().equals("")) {
	// st.executeUpdate(inst[i]);
	// System.out.println(">>" + inst[i]);
	// }
	// }
	//
	// } catch (Exception e) {
	// System.out.println("*** Error : " + e.toString());
	// System.out.println("*** ");
	// System.out.println("*** Error : ");
	// e.printStackTrace();
	// System.out.println("################################################");
	// System.out.println(sb.toString());
	// }
	//
	// HibernateUtil.startSession();
	//
	// }

	// public void update(String dbUserName, String dbPassword, String dbName,
	// String filePath, UtilityPopup uP) throws SQLException {
	//
	// DatabaseTool.uP = uP;
	// DatabaseTool.dbUserName = dbUserName;
	// DatabaseTool.dbPassword = dbPassword;
	// DatabaseTool.dbName = dbName;
	// DatabaseTool.filePath = filePath;
	//
	// Thread thread = new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	//
	// boolean isRunning = true;
	//
	// while (isRunning) {
	//
	// HibernateUtil.endSession();
	//
	// String s = new String();
	// StringBuffer sb = new StringBuffer();
	//
	// try {
	// FileReader fr = new FileReader(new File(DatabaseTool.filePath));
	// BufferedReader br = new BufferedReader(fr);
	//
	// while ((s = br.readLine()) != null) {
	// sb.append(s + "\n");
	// }
	// br.close();
	//
	// // here is our splitter ! We use ";" as a delimiter for each
	// // request
	// // then we are sure to have well formed statements
	// String[] inst = sb.toString().split(";");
	//
	// Connection c = DatabaseTool.getConnection(DatabaseTool.dbUserName,
	// DatabaseTool.dbPassword, DatabaseTool.dbName);
	// Statement st = c.createStatement();
	//
	// for (int i = 0; i < inst.length; i++) {
	// // we ensure that there is no spaces before or after the
	// // request
	// // string
	// // in order to not execute empty statements
	// if (!inst[i].trim().equals("")) {
	// st.executeUpdate(inst[i]);
	// System.out.println(">>" + inst[i]);
	// }
	//
	// }
	//
	// DatabaseTool.uP.dispose();
	// new SuccessPopup("DB Recovery").setVisible(true);
	// Values.topPanel.closeBalloonPanel();
	//
	// isRunning = false;
	//
	// HibernateUtil.startSession();
	//
	// } catch (Exception e) {
	// System.out.println("*** Error : " + e.toString());
	// System.out.println("*** ");
	// System.out.println("*** Error : ");
	// e.printStackTrace();
	// System.out.println("################################################");
	// System.out.println(sb.toString());
	// }
	//
	// }
	// }
	// });
	//
	// thread.start();
	// uP.showProgressBar();
	//
	// }

	public void decryptedUpdate(String dbUserName, String dbPassword, String dbName, String path, UtilityPopup uP) throws SQLException {

		DatabaseTool.uP = uP;
		DatabaseTool.dbUserName = dbUserName;
		DatabaseTool.dbPassword = dbPassword;
		DatabaseTool.dbName = dbName;
		DatabaseTool.filePath = path;

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				boolean isRunning = true;

				while (isRunning) {

					HibernateUtil.endSession();

					String s = new String();
					StringBuffer sb = new StringBuffer();

					try {

						File original = new File(filePath);
						File recovery = new File(TEMP_FILE);
						SecurityTool.decryptFile(original, recovery);

						FileReader fr = new FileReader(recovery);
						BufferedReader br = new BufferedReader(fr);

						while ((s = br.readLine()) != null) {
							sb.append(s + "\n");
						}
						br.close();

						// here is our splitter ! We use ";" as a delimiter for each
						// request
						// then we are sure to have well formed statements
						String[] inst = sb.toString().split(";");

						Connection c = DatabaseTool.getConnection(DatabaseTool.dbUserName, DatabaseTool.dbPassword, DatabaseTool.dbName);
						Statement st = c.createStatement();

						for (int i = 0; i < inst.length; i++) {
							// we ensure that there is no spaces before or after the
							// request
							// string
							// in order to not execute empty statements
							if (!inst[i].trim().equals("")) {
								st.executeUpdate(inst[i]);
								System.out.println(">>" + inst[i]);
							}

						}

						recovery.delete();

						DatabaseTool.uP.dispose();
						new SuccessPopup("DB Recovery").setVisible(true);
						Values.topPanel.closeBalloonPanel();

						isRunning = false;

						HibernateUtil.startSession();

					} catch (Exception e) {
						System.out.println("*** Error : " + e.toString());
						System.out.println("*** ");
						System.out.println("*** Error : ");
						e.printStackTrace();
						System.out.println("################################################");
						System.out.println(sb.toString());
					}

				}
			}
		});

		thread.start();
		uP.showProgressBar();

	}

	// public void reset(String dbUserName, String dbPassword, String dbName)
	// throws SQLException {
	// update(dbUserName, dbPassword, dbName, RESET_FILE, uP);
	// }

	public static void executeUpdates() {

		try {
			if (DatabaseSettings.getInstance().getDbVersion() == 1.0d) {
				HibernateUtil.endSession();
				Connection con = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(HibernateUtil.URL + Credentials.getInstance().getDatabaseName(),
							SecurityTool.decryptString(Credentials.getInstance().getUsername()),
							SecurityTool.decryptString(Credentials.getInstance().getPassword()));
					try {
						Statement st = con.createStatement();
						st.executeUpdate("alter table product drop sold_today_in_sack");
						System.out.println("Column sold_today_in_sack is deleted successfully!");
						st.executeUpdate("alter table product drop sold_today_in_kilo");
						System.out.println("Column sold_today_in_kilo is deleted successfully!");
						st.executeUpdate("alter table product drop alert_using_sack");
						System.out.println("Column alert_using_sack is deleted successfully!");
						st.executeUpdate("alter table product drop alert_using_kilo");
						System.out.println("Column alert_using_kilo is deleted successfully!");

						// increment db version of database
						double current = DatabaseSettings.getInstance().getDbVersion();
						DatabaseSettings.getInstance().setDbVersion(current + 0.1d);
						DatabaseSettings.getInstance().persist();
						AppSettings.getInstance().setAppVersion(current + 0.1d);
						AppSettings.getInstance().persist();

					} catch (SQLException s) {
						System.out.println("Table or column is not found!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				HibernateUtil.startSession();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
