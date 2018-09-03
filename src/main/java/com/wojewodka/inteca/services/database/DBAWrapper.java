package com.wojewodka.inteca.services.database;

@FunctionalInterface
public interface DBAWrapper {

	@SuppressWarnings("rawtypes")
	<T> T run(DBAction action);
	
}
