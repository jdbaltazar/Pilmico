package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DatabaseSettings {

	private static DatabaseSettings databaseSettings = null;
	private static String databaseSettingsFileName = "dbsettings.xml";
	private String filePath;
	private double dbVersion;

	private DatabaseSettings() {
		super();
	}

	public DatabaseSettings(String filePath, double dbVersion) {
		super();
		this.filePath = filePath;
		this.dbVersion = dbVersion;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public double getDbVersion() {
		return dbVersion;
	}

	public void setDbVersion(double dbVersion) {
		this.dbVersion = dbVersion;
	}

	public static DatabaseSettings getInstance() throws FileNotFoundException {
		if (databaseSettings == null) {
			// load database settings
			XStream xs = new XStream(new DomDriver());
			xs.alias("databaseSettings", DatabaseSettings.class);
			databaseSettings = new DatabaseSettings();
			FileInputStream fis = new FileInputStream(databaseSettingsFileName);
			xs.fromXML(fis, databaseSettings);
		}
		return databaseSettings;
	}

	public void persist() {

		try {
			XStream xs = new XStream(new DomDriver());
			xs.alias("databaseSettings", DatabaseSettings.class);
			File file = new File(databaseSettingsFileName);
			FileOutputStream fs = new FileOutputStream(file);
			xs.toXML(databaseSettings, fs);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
