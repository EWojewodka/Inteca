package com.wojewodka.inteca.services.database;

import java.sql.Connection;

@FunctionalInterface
public interface DBAction<T> {

	T run(Connection connection) throws Exception;

}
