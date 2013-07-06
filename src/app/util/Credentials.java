package app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Credentials {

	private static Credentials credentials = null;
	private static String hibernateConnectionCredentialsFileName = "config.xml";
	private String hibernateConnectionUsername;
	private String hibernateConnectionPassword;
	private String hibernateConnectionUrl;

	private Credentials() {
		super();
	}

	private Credentials(String hibernateConnectionUsername, String hibernateConnectionPassword, String hibernateConnectionUrl) {
		super();
		this.hibernateConnectionUsername = hibernateConnectionUsername;
		this.hibernateConnectionPassword = hibernateConnectionPassword;
		this.hibernateConnectionUrl = hibernateConnectionUrl;
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

	public String getHibernateConnectionUsername() {
		return hibernateConnectionUsername;
	}

	public void setHibernateConnectionUsername(String hibernateConnectionUsername) {
		this.hibernateConnectionUsername = hibernateConnectionUsername;
	}

	public String getHibernateConnectionPassword() {
		return hibernateConnectionPassword;
	}

	public void setHibernateConnectionPassword(String hibernateConnectionPassword) {
		this.hibernateConnectionPassword = hibernateConnectionPassword;
	}

	public String getHibernateConnectionUrl() {
		return hibernateConnectionUrl;
	}

	public void setHibernateConnectionUrl(String hibernateConnectionUrl) {
		this.hibernateConnectionUrl = hibernateConnectionUrl;
	}

}
