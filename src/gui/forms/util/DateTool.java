package gui.forms.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTool {

	public static final long DAY_DIFFERENCE = 86400000L;

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static List<Date> setDatesToDateWithoutTime(List<Date> dates) {
		for (Date d : dates) {
			d = getDateWithoutTime(d);
		}
		return dates;
	}

	public static Date getTomorrowDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date getNoonTimeForDate(Date date) {
		date = getDateWithoutTime(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 12);
		return cal.getTime();
	}

	public static List<Date> addUniqueDatesRemoveTime(List<Date> original, List<Date> datesToAdd) {
		for (Date d : datesToAdd) {
			d = getDateWithoutTime(d);
			if (!containsRemoveTime(original, d)) {
				original.add(d);
			}
		}
		return original;
	}

	public static boolean containsRemoveTime(List<Date> original, Date date) {
		date = getDateWithoutTime(date);
		for (Date d : original) {
			d = getDateWithoutTime(d);
			if (d.compareTo(date) == 0) {
				return true;
			}
		}
		return false;
	}

	public static List<Date> copyDatesRemoveTime(List<Date> dates) {
		List<Date> copy = new ArrayList<Date>();
		for (Date d : dates) {
			copy.add(getDateWithoutTime(d));
		}
		return copy;
	}

	public static List<Date> copyDates(List<Date> dates) {
		List<Date> copy = new ArrayList<Date>();
		for (Date d : dates) {
			copy.add(d);
		}
		return copy;
	}

	public static List<Date> sortDateEarliestFirst(List<Date> dates) {
		List<Date> sorted = new ArrayList<Date>();
		List<Date> temp = copyDates(dates);
		int size = temp.size();
		for (int i = 0; i < size; i++) {
			Date earliest = getEarliestDate(dates);
			sorted.add(earliest);
			dates.remove(dates.indexOf(earliest));
		}

		return sorted;
	}

	public static Date getEarliestDate(List<Date> dates) {
		if (dates.size() == 0)
			return null;
		Date earliest = dates.get(0);
		for (Date d : dates) {
			if (d.before(earliest))
				earliest = d;
		}
		return earliest;
	}

}
