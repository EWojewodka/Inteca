package com.wojewodka.inteca.utils;

import com.wojewodka.inteca.services.dbo.AnnotationManager;
import com.wojewodka.inteca.services.dbo.DatabaseObject;
import com.wojewodka.inteca.services.dbo.EntityMetadata;

public class EntityUtils {

	public static int getId(DatabaseObject dbo) {
		EntityMetadata info = AnnotationManager.getInstance().getInfo(dbo.getClass());
		try {
			return info.getIdField().getInt(dbo);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
