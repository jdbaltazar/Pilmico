package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AppSettings {

	private static AppSettings appSettings = null;
	private static String appSettingsFileName = "appsettings.xml";
	private double appVersion;

	private AppSettings() {
		super();
	}

	public AppSettings(double appVersion) {
		super();
		this.appVersion = appVersion;
	}

	public double getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(double appVersion) {
		this.appVersion = appVersion;
	}

	public static AppSettings getInstance() throws FileNotFoundException {
		if (appSettings == null) {
			// load app settings
			XStream xs = new XStream(new DomDriver());
			xs.alias("appSettings", AppSettings.class);
			appSettings = new AppSettings();
			FileInputStream fis = new FileInputStream(appSettingsFileName);
			xs.fromXML(fis, appSettings);
		}
		return appSettings;
	}

	public void persist() {

		try {
			XStream xs = new XStream(new DomDriver());
			xs.alias("appSettings", AppSettings.class);
			File file = new File(appSettingsFileName);
			FileOutputStream fs = new FileOutputStream(file);
			xs.toXML(appSettings, fs);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
