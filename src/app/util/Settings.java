package app.util;

public class Settings {

	private String defaultDatabaseRecoveryFileName;

	public Settings() {
		super();
	}

	public Settings(String defaultDatabaseRecoveryFileName) {
		super();
		this.defaultDatabaseRecoveryFileName = defaultDatabaseRecoveryFileName;
	}

	public String getDefaultDatabaseRecoveryFileName() {
		return defaultDatabaseRecoveryFileName;
	}

	public void setDefaultDatabaseRecoveryFileName(String defaultDatabaseRecoveryFileName) {
		this.defaultDatabaseRecoveryFileName = defaultDatabaseRecoveryFileName;
	}

}
