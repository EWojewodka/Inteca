package com.wojewodka.inteca.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	public static String formatDate(Date date, String format) {
		if (date == null)
			throw new NullPointerException();
		if (StringUtils.isEmpty(format))
			format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

}
