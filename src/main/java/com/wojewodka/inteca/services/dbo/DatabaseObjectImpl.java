package com.wojewodka.inteca.services.dbo;

import java.util.HashMap;
import java.util.Map;

public abstract class DatabaseObjectImpl implements DatabaseObject {

	private Map<String, Object> fields = new HashMap<>();

	@Override
	public Map<String, Object> getFields() {
		return fields;
	}

	@Override
	public Object getField(String columnName) {
		return fields.get(columnName);
	}

	@Override
	public void setField(String name, Object value) {
		fields.put(name, value);
	}

}
