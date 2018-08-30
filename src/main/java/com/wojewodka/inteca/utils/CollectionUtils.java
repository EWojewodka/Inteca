package com.wojewodka.inteca.utils;

import java.util.Collection;
import java.util.StringJoiner;

public class CollectionUtils {

	public static boolean isEmpty(Object[] objs) {
		return objs == null || objs.length == 0;
	}

	/**
	 * Return string with concat objects. </br>
	 * All of these objects are convert to {@link String} by
	 * {@link String#valueOf(Object)}. For example if passed object is a
	 * {@link Class} type and second of these is a {@link StringUtils} type returned
	 * {@link String} will be like this: </br>
	 * <code>"class com.wojewodka.example.class.Type[splitter]StringUtils@123dsa456"</code>
	 * </br>
	 * Note: If there are no objects - return empty string. Also, if any of object
	 * is null, it will be "null" String. - for more information go to
	 * {@link String#valueOf(Object)}
	 * 
	 * @param objs
	 * @param splitter
	 * @return
	 */
	public static String toGenericString(Object[] objs, String splitter) {
		if (objs == null)
			return "";
		StringJoiner joiner = new StringJoiner(splitter);
		for (Object obj : objs)
			joiner.add(String.valueOf(obj)); // not use toString(), cause we don't check object is not null.
		return joiner.toString();
	}

	public static String toGenericString(Collection<?> collection, String splitter) {
		if (collection == null || collection.isEmpty())
			return "";
		return toGenericString(collection.toArray(new Object[collection.size()]), splitter);
	}

}
