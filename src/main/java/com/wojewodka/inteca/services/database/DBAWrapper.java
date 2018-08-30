package com.wojewodka.inteca.services.database;

public interface DBAWrapper {

	@SuppressWarnings("rawtypes")
	<T> T run(DBAction action);
	
}
