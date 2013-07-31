package gui.forms.util;

import java.util.Calendar;
import java.util.Date;

public class DateWithoutTime {

	public static Date getDateWithoutTime(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		// date = cal.getTime();

		return date;
	}

}
