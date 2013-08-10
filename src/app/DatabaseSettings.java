package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DatabaseSettings {

	private static DatabaseSettings databaseSettings = null;
	private static String databaseSettingsFileName = "dbsettings.xml";
	private String filePath;

	private DatabaseSettings() {
		super();
	}

	public DatabaseSettings(String filePath) {
		super();
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

}
