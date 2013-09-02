package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AppSettings {

	public static final String APP_FILE_TYPE = "pilmico";
	// private static AppSettings appSettings = null;
	private static String appSettingsFileName = "appsettings.xml";
	private float appVersion;

	private AppSettings() {
		super();
	}

	public AppSettings(float appVersion) {
		super();
		this.appVersion = appVersion;
	}

	public float getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(float appVersion) {
		this.appVersion = appVersion;
	}

	public static AppSettings getInstance() throws FileNotFoundException {
		// if (appSettings == null) {
		// load app settings
		XStream xs = new XStream(new DomDriver());
		xs.alias("appSettings", AppSettings.class);
		AppSettings appSettings = new AppSettings();
		FileInputStream fis = new FileInputStream(appSettingsFileName);
		xs.fromXML(fis, appSettings);
		// }
		return appSettings;
	}

	public void persist() {
		try {
			XStream xs = new XStream(new DomDriver());
			xs.alias("appSettings", AppSettings.class);
			File file = new File(appSettingsFileName);
			FileOutputStream fs = new FileOutputStream(file);
			xs.toXML(new AppSettings(appVersion), fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
