package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Credentials {

	private static Credentials credentials = null;
	private static String hibernateConnectionCredentialsFileName = "credentials.xml";
	private String username;
	private String password;
	private String databaseName;

	private Credentials() {
		super();
	}

	private Credentials(String username, String password, String databaseName) {
		super();
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
	}

	public static Credentials getInstance() throws FileNotFoundException {
		if (credentials == null) {
			// load credentials
			XStream xs = new XStream(new DomDriver());
			xs.alias("credentials", Credentials.class);
			credentials = new Credentials();
			FileInputStream fis = new FileInputStream(hibernateConnectionCredentialsFileName);
			xs.fromXML(fis, credentials);
		}
		return credentials;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

}
