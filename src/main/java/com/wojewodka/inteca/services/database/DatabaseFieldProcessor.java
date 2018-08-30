package com.wojewodka.inteca.services.database;

import java.util.Date;

import com.wojewodka.inteca.utils.DateUtils;

public class DatabaseFieldProcessor {

	/**
	 * Return string prepared & escaped for using in SQL query. </br>
	 * All of using values should be processed here! </br>
	 * 
	 * @param obj
	 * @return
	 */
	public static String process(Object obj) {
		String result = null;
		if (obj instanceof String)
			result = processString((String) obj);
		else if (obj instanceof Number)
			result = processNumeric((Number) obj);
		else if (obj instanceof Date)
			result = processDate((Date) obj);
		return result;
	}

	private static String processString(String s) {
		return "'" + s + "'";
	}

	private static String processNumeric(Number number) {
		return number.toString();
	}

	private static String processDate(Date date) {
		return "'" + DateUtils.formatDate(date, "yyyy-MM-dd");
	}

}
