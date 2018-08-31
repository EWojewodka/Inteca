package com.wojewodka.inteca.services.database;

import static java.sql.JDBCType.BOOLEAN;
import static java.sql.JDBCType.DATE;
import static java.sql.JDBCType.INTEGER;
import static java.sql.JDBCType.NULL;
import static java.sql.JDBCType.VARCHAR;

import java.sql.PreparedStatement;
import java.sql.SQLType;
import java.util.Date;

import com.wojewodka.inteca.services.NotImplementedException;
import com.wojewodka.inteca.services.repository.RepositoryImpl;

public class DatabaseFieldProcessor {

	/**
	 * Return {@link SQLType} for using in
	 * {@link PreparedStatement#setObject(int, Object, SQLType)}
	 * 
	 * @see RepositoryImpl
	 * @param obj
	 * @return
	 */
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
