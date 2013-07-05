package common.util;

public class Credentials {

	private String hibernateConnectionUsername;
	private String hibernateConnectionPassword;
	private String hibernateConnectionUrl;

	public Credentials() {
		super();
	}

	public Credentials(String hibernateConnectionUsername, String hibernateConnectionPassword, String hibernateConnectionUrl) {
		super();
		this.hibernateConnectionUsername = hibernateConnectionUsername;
		this.hibernateConnectionPassword = hibernateConnectionPassword;
		this.hibernateConnectionUrl = hibernateConnectionUrl;
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
