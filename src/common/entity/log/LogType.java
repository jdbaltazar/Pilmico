package common.entity.log;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "LogType")
public class LogType {

	public static final String SYSTEM = "System";
	public static final String ERROR = "Error";

	@Id
	private String name;

	public LogType() {
		super();
	}

	public LogType(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
