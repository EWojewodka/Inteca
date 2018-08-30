package com.wojewodka.inteca.services.database;

import static java.sql.JDBCType.*;

import java.sql.SQLType;
import java.util.Date;

import com.wojewodka.inteca.services.NotImplementedException;

public class DatabaseFieldProcessor {

	public static SQLType getSqlType(Object obj) {
		if (obj == null)
			return NULL;
		else if (obj instanceof String)
			return VARCHAR;
		else if (obj instanceof Integer)
			return INTEGER;
		else if (obj instanceof Boolean)
			return BOOLEAN;
		else if (obj instanceof Date)
			return DATE;

		throw new NotImplementedException("SQL type for [" + obj.getClass() + " ] class.");
	}

}
