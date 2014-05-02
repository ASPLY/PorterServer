package org.a_sply.porter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	private static String PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);

	public static String format(Date sending) {
		return sdf.format(sending);
	}

	public static String pattern() {
		return PATTERN;
	}

}
