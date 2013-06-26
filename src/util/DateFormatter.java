package util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateFormatter {

	public static DateFormatter dateFormatter = null;
	private SimpleDateFormat sdf;

	public DateFormatter() {
		super();
	}

	public static DateFormatter getInstance() {
		if (dateFormatter == null)
			dateFormatter = new DateFormatter();

		return dateFormatter;
	}

	public SimpleDateFormat getFormat(int format) {

		switch (format) {

		case Utility.HMFormat:
			sdf = new SimpleDateFormat("hh:mm a");
			break;

		case Utility.HMSFormat:
			sdf = new SimpleDateFormat("hh:mm:ss a");
			break;

		case Utility.EMDYFormat:
			sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
			break;

		case Utility.MDYFormat:
			sdf = new SimpleDateFormat("MMM dd, yyyy");
			break;

		case Utility.CompleteFormat:
			sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
			break;

		case Utility.CompleteFormatWithoutSec:
			sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
			break;

		case Utility.MySqlFormat:
			sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			break;

		default:
			break;
		}

		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

		return sdf;
	}

}
