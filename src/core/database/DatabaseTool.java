package core.database;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import core.persist.HibernateUtil;

public class DatabaseTool {

	public static final String RESET_FILE = "data/pilmico-create.sql";

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

	public static boolean backup(String dbUserName, String dbPassword, String dbName, String path) {

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

	public static void update(String dbUserName, String dbPassword, String dbName, String filePath) throws SQLException {

		String s = new String();
		StringBuffer sb = new StringBuffer();

		try {
			FileReader fr = new FileReader(new File(filePath));
			// be sure to not have line starting with "--" or "/*" or any other non
			// aplhabetical character

			BufferedReader br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				sb.append(s);
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

	}

	public static void reset(String dbUserName, String dbPassword, String dbName) throws SQLException {
		update(dbUserName, dbPassword, dbName, RESET_FILE);
	}

}