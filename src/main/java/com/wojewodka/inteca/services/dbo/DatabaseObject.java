package com.wojewodka.inteca.services.dbo;

import java.util.Map;

import com.wojewodka.inteca.services.repository.Repository;

/**
 * Shared interface for database objects.
 * 
 * @see Repository
 * @author ewojewodka
 *
 */
public interface DatabaseObject {

	Map<String, Object> getFields();

	Object getField(String columnName);
	
	void setField(String name, Object value);
}
