package core.database;

import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import util.Values;

import core.persist.HibernateUtil;

public class DatabaseTool {

	public static final String RESET_FILE = "data/pilmico-create.sql";
	public static Process runtimeProcess;
	private static UtilityPopup uP;
	private Thread thread;
	private static DatabaseTool ins;
	private static String dbUserName, dbPassword, dbName,filePath;
	
	public static DatabaseTool getInstance(){

		if(ins == null)
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

	public boolean backup(String dbUserName, String dbPassword, String dbName, String path, UtilityPopup uP) {

		DatabaseTool.uP = uP;
		
		String executeCmd = "mysqldump -u " + dbUserName;
		if (!dbPassword.equals(""))
			executeCmd = executeCmd + " -p" + dbPassword;
		executeCmd = executeCmd + " --add-drop-database -B " + dbName + " -r " + path;

		try {
			System.out.println(executeCmd);// this out put works in mysql shell
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			
//			int processComplete = 1;
			thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					boolean isRunning = true;
					
					while (isRunning) {
						try {
							int processComplete = runtimeProcess.waitFor();

							if (processComplete == 0) {
								DatabaseTool.uP.dispose();
								new SuccessPopup("DB Backup").setVisible(true);
								Values.topPanel.closeBalloonPanel();
								isRunning = false;

							} else {
								DatabaseTool.uP.dispose();
								JOptionPane.showMessageDialog(Values.mainFrame,
										"Could not create the backup", "Error",
										JOptionPane.ERROR_MESSAGE);
								isRunning = false;
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
			thread.start();
			uP.showProgressBar();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void decryptAndUpdate(String dbUserName, String dbPassword, String dbName, String filePath, UtilityPopup uP) throws SQLException {
		update(dbUserName, dbPassword, dbName, filePath, uP);
	}

	public File encryptFile(File file) {

		return null;
	}

	public static void update(String dbUserName, String dbPassword, String dbName, File file) throws SQLException {

		HibernateUtil.endSession();

		String s = new String();
		StringBuffer sb = new StringBuffer();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}
			br.close();

			// here is our splitter ! We use ";" as a delimiter for each request
			// then we are sure to have well formed statements
			String[] inst = sb.toString().split(";");

			Connection c = DatabaseTool.getConnection(dbUserName, dbPassword, dbName);
			Statement st = c.createStatement();

			for (int i = 0; i < inst.length; i++) {
				// we ensure that there is no spaces before or after the request
				// string
				// in order to not execute empty statements
				if (!inst[i].trim().equals("")) {
					st.executeUpdate(inst[i]);
					System.out.println(">>" + inst[i]);
				}
			}

		} catch (Exception e) {
			System.out.println("*** Error : " + e.toString());
			System.out.println("*** ");
			System.out.println("*** Error : ");
			e.printStackTrace();
			System.out.println("################################################");
			System.out.println(sb.toString());
		}

		HibernateUtil.startSession();

	}

	public void update(String dbUserName, String dbPassword, String dbName, String filePath, UtilityPopup uP) throws SQLException {

		DatabaseTool.uP = uP;
		DatabaseTool.dbUserName = dbUserName;
		DatabaseTool.dbPassword = dbPassword;
		DatabaseTool.dbName = dbName;
		DatabaseTool.filePath = filePath;

		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				boolean isRunning = true;
				
				while (isRunning) {
					
					HibernateUtil.endSession();
					
					
					String s = new String();
					StringBuffer sb = new StringBuffer();

					try {
						FileReader fr = new FileReader(new File(DatabaseTool.filePath));
						BufferedReader br = new BufferedReader(fr);

						while ((s = br.readLine()) != null) {
							sb.append(s + "\n");
						}
						br.close();

						// here is our splitter ! We use ";" as a delimiter for each request
						// then we are sure to have well formed statements
						String[] inst = sb.toString().split(";");

						Connection c = DatabaseTool.getConnection(DatabaseTool.dbUserName, DatabaseTool.dbPassword, DatabaseTool.dbName);
						Statement st = c.createStatement();

						for (int i = 0; i < inst.length; i++) {
							// we ensure that there is no spaces before or after the request
							// string
							// in order to not execute empty statements
							if (!inst[i].trim().equals("")) {
								st.executeUpdate(inst[i]);
								System.out.println(">>" + inst[i]);
							}
							
						}
						
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

	public void reset(String dbUserName, String dbPassword, String dbName) throws SQLException {
		update(dbUserName, dbPassword, dbName, RESET_FILE, uP);
	}
	
}
