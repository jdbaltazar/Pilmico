package core.database;

import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Point;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;

import common.entity.product.Product;
import common.manager.Manager;

import app.AppSettings;
import app.Credentials;
import app.DatabaseSettings;

import util.DateFormatter;
import util.Utility;
import util.Values;

import core.persist.HibernateUtil;
import core.security.SecurityTool;

public class DatabaseTool {

	public static final String RESET_FILE = "data/pilmico-create.sql";
	public static final String BACKUP_TEMP_FILE = "temp/btemp.tmp";
	public static final String RECOVERY_TEMP_FILE = "temp/rtemp.tmp";
	public static final String INTERNAL_BACKUP_PATH = "backup/";
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
			// System.out.println("*** Driver loaded");
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

	public static boolean encryptedBackup(String dbUserName, String dbPassword, String dbName, String path, UtilityPopup uP) {

		HibernateUtil.endSession();
		DatabaseTool.uP = uP;

		filePath = path;
		String executeCmd = "mysqldump -u " + dbUserName;
		if (!dbPassword.equals(""))
			executeCmd = executeCmd + " -p" + dbPassword;
		executeCmd = executeCmd + " --add-drop-database -B " + dbName + " -r " + BACKUP_TEMP_FILE;

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
									File original = new File(BACKUP_TEMP_FILE);
									File internalBackUp = new File(INTERNAL_BACKUP_PATH + "Pilmico Backup "
											+ DateFormatter.getInstance().getFormat(Utility.DMYFormat).format(new Date()) + "." + AppSettings.APP_FILE_TYPE);
									File backUp = new File(filePath);
									SecurityTool.encryptAndWriteFile(original, internalBackUp);
									if (DatabaseSettings.getInstance().isFilePathSet())
										SecurityTool.encryptAndWriteFile(original, backUp);
									original.delete();

								} catch (Exception e) {
									e.printStackTrace();
								}

								if (DatabaseTool.uP != null)
									DatabaseTool.uP.dispose();
								new SuccessPopup("Database Backup").setVisible(true);
								Values.topPanel.closeBalloonPanel();
								isRunning = false;
								
							} else {
								if (DatabaseTool.uP != null)
									DatabaseTool.uP.dispose();
								JOptionPane.showMessageDialog(Values.mainFrame, "Could not create the backup", "Error", JOptionPane.ERROR_MESSAGE);
								isRunning = false;
							}
							
							Thread.currentThread().interrupt();
							
						} catch (InterruptedException e) {
							//e.printStackTrace();
							System.out.println("Backup thread interrupted");
						}
					}
				}
			});

			thread.start();

			if (uP != null)
				uP.showProgressBar();
			else {
				DatabaseTool.uP = new UtilityPopup(new Point(), Values.AUTO_BACKUP);
				DatabaseTool.uP.setVisible(true);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		HibernateUtil.startSession();
		return false;
	}

	// public void decryptedUpdate(String dbUserName, String dbPassword, String
	// dbName, String path, UtilityPopup uP) throws SQLException {
	//
	// DatabaseTool.uP = uP;
	// DatabaseTool.dbUserName = dbUserName;
	// DatabaseTool.dbPassword = dbPassword;
	// DatabaseTool.dbName = dbName;
	// DatabaseTool.filePath = path;
	//
	// HibernateUtil.endSession();
	//
	// String executeCmd = "mysqldump -u " + dbUserName;
	// if (!dbPassword.equals(""))
	// executeCmd = executeCmd + " -p" + dbPassword;
	// executeCmd = executeCmd + " --add-drop-database -B " + dbName + " -r " +
	// BACKUP_TEMP_FILE;
	//
	// System.out.println(executeCmd);// this out put works in mysql shell
	//
	// try {
	//
	// // create backup file in case of failure
	// System.out.println("Creating a backup in case of failure");
	// runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	//
	// Thread thread = new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	//
	// boolean isRunning = true;
	// boolean successful = false;
	//
	// while (isRunning) {
	//
	// File backupOriginal = new File(BACKUP_TEMP_FILE);
	// System.out.println("Backup created");
	//
	// // decrypt and update
	//
	// System.out.println("Starting decryption..");
	// String s = new String();
	// StringBuffer sb = new StringBuffer();
	//
	// try {
	//
	// File recoveryOriginal = new File(filePath);
	// File recovery = new File(RECOVERY_TEMP_FILE);
	// SecurityTool.decryptFile(recoveryOriginal, recovery);
	//
	// System.out.println("Done decrypting. Starting recovery..");
	// FileReader fr = new FileReader(recovery);
	// BufferedReader br = new BufferedReader(fr);
	//
	// while ((s = br.readLine()) != null) {
	// sb.append(s + "\n");
	// }
	// br.close();
	//
	// // here is our splitter ! We use ";" as a delimiter for
	// // each
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
	// // System.out.println(">>" + inst[i]);
	// }
	//
	// }
	//
	// System.out.println("Done with recovery..");
	// backupOriginal.delete();
	// recovery.delete();
	//
	// DatabaseTool.uP.dispose();
	// new SuccessPopup("DB Recovery").setVisible(true);
	// Values.topPanel.closeBalloonPanel();
	//
	// successful = true;
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
	// } finally {
	//
	// if (!successful) {
	//
	// // show ui here to inform user that recovery was not successful
	//
	// // revert changes using the backup file created
	//
	// System.out.println("Reverting changes..");
	// String s = new String();
	// StringBuffer sb = new StringBuffer();
	//
	//
	// File backupOriginal = new File(BACKUP_TEMP_FILE);
	//
	// FileReader fr = new FileReader(backupOriginal);
	// BufferedReader br = new BufferedReader(fr);
	//
	// while ((s = br.readLine()) != null) {
	// sb.append(s + "\n");
	// }
	// br.close();
	//
	// // here is our splitter ! We use ";" as a delimiter for
	// // each
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
	// // System.out.println(">>" + inst[i]);
	// }
	//
	// }
	//
	// }
	// }
	//
	// }
	//
	// }
	// }
	// });
	//
	// thread.start();
	// uP.showProgressBar();
	//
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
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
						File recovery = new File(RECOVERY_TEMP_FILE);
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
						new SuccessPopup("Database Recovery").setVisible(true);
						Values.topPanel.closeBalloonPanel();

						isRunning = false;

						Thread.currentThread().interrupt();
						
						HibernateUtil.startSession();

					}catch (InterruptedException e) {
						//e.printStackTrace();
						System.out.println("Recover thread interrupted");
					} 
					catch (Exception e) {
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
			if (DatabaseSettings.getInstance().getDbVersion() == 1.0f) {
				HibernateUtil.endSession();
				Connection con = null;

				// (1) Remove unnecessary columns in product table
				// (2) Add default kilos per sack values for all products
				// (3) Update the name of 'sprinter' to 'sprinter pack'

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
						float current = DatabaseSettings.getInstance().getDbVersion();
						DatabaseSettings.getInstance().setDbVersion(current + 0.1f);
						DatabaseSettings.getInstance().persist();
						AppSettings.getInstance().setAppVersion(current + 0.1f);
						AppSettings.getInstance().persist();

					} catch (SQLException s) {
						System.out.println("Table or column is not found!");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				// update the kilos per sack
				List<Product> products = new ArrayList<Product>();

				try {
					products = Manager.productManager.getProducts();

					for (Product p : products) {

						if (p.getId() == 1) {
							p.setKilosPerSack(1);
						} else if (p.getId() == 2) {
							p.setKilosPerSack(24);
							// update name from sprinter ---> sprinter pack
							p.setName("SPRINTER PACK");
						} else if (p.getId() == 3 || p.getId() == 4 || p.getId() == 16 || p.getId() == 17 || p.getId() == 18 || p.getId() == 19
								|| p.getId() == 20 || p.getId() == 21 || p.getId() == 22 || p.getId() == 27) {
							p.setKilosPerSack(25);
						} else if (p.getId() == 5 || p.getId() == 6 || p.getId() == 7 || p.getId() == 8 || p.getId() == 9 || p.getId() == 10
								|| p.getId() == 11 || p.getId() == 12 || p.getId() == 13 || p.getId() == 14 || p.getId() == 15 || p.getId() == 23
								|| p.getId() == 24 || p.getId() == 25 || p.getId() == 28) {
							p.setKilosPerSack(50);
						} else if (p.getId() == 26) {
							p.setKilosPerSack(10);
						}
						Manager.productManager.updateProduct(p);
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

		try {

			// drop the display columns in product
			if (DatabaseSettings.getInstance().getDbVersion() == 1.1f) {

				HibernateUtil.endSession();
				Connection con = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(HibernateUtil.URL + Credentials.getInstance().getDatabaseName(),
							SecurityTool.decryptString(Credentials.getInstance().getUsername()),
							SecurityTool.decryptString(Credentials.getInstance().getPassword()));
					try {

						Statement st = con.createStatement();

						// insert more code here

						st.executeUpdate("alter table product drop display_in_sack");
						System.out.println("Column display_in_sack is deleted successfully!");
						st.executeUpdate("alter table product drop display_in_kilo");
						System.out.println("Column display_in_kilo is deleted successfully!");

						// increment db version of database
						float current = DatabaseSettings.getInstance().getDbVersion();
						DatabaseSettings.getInstance().setDbVersion(current + 0.1f);
						DatabaseSettings.getInstance().persist();
						AppSettings.getInstance().setAppVersion(current + 0.1f);
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

		// For the final version!
		// (1) Add kilos per sack column to the ff tables:
		// - deliveries
		// - pullouts
		// - sales
		// - ar
		// - inventory sheet
		// (2) Set kilos per sack of all details to the current kilos per sack
		// of products

	}

}
