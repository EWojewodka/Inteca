package com.wojewodka.inteca.services.dbo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wojewodka.inteca.services.dbo.annotations.DBColumn;
import com.wojewodka.inteca.services.dbo.annotations.DBTable;
import com.wojewodka.inteca.services.dbo.annotations.Id;
import com.wojewodka.inteca.utils.CollectionUtils;

public class EntityMetadata {

	private Class<?> clazz;

	private String tableName;

	private IdInfo idInfo;

	/**
	 * It's better way to use a {@link LinkedHashMap} than {@link HashMap}. </br>
	 * Look at <a href=
	 * "https://stackoverflow.com/questions/12998568/hashmap-vs-linkedhashmap-performance-in-iteration-over-values">StackOverFlow</a>
	 */
	private Map<String, Field> annotatedEntityFields = new LinkedHashMap<>();

	EntityMetadata(Class<?> clazz) {
		this.clazz = clazz;
	}

	void analyze() {
		DBTable dbTable = clazz.getAnnotation(DBTable.class);
		if (dbTable == null)
			throw new NotEntityClassException(clazz);
		this.tableName = dbTable.name();

		Field[] fields = clazz.getDeclaredFields();
		if (CollectionUtils.isEmpty(fields))
			return;

		for (Field field : fields) {
			field.setAccessible(true);

			DBColumn dbColumn = field.getDeclaredAnnotation(DBColumn.class);
			if (dbColumn == null) {
				Id idColumn = field.getDeclaredAnnotation(Id.class);
				if (idColumn == null)
					continue;

				idInfo = new IdInfo(idColumn.name(), field);
				continue;
			}

			annotatedEntityFields.put(dbColumn.name(), field);
		}

	}

	public Class<?> getEntityClass() {
		return clazz;
	}

	public String getTableName() {
		return tableName;
	}

	public Map<String, Field> getColumns() {
		return annotatedEntityFields;
	}

	public Field getFieldForColumn(String columnName) {
		Field result = annotatedEntityFields.get(columnName);
		if(result == null && columnName.equals(idInfo.columnName))
			result = idInfo.field;
		return result;
	}

	public String getColumnNameForField(Field field) {
		Set<Entry<String, Field>> set = annotatedEntityFields.entrySet();
		for (Entry<String, Field> en : set) {
			if (en.getValue().equals(field))
				return en.getKey();
		}
		return null;
	}

	public String getIdColumnName() {
		return idInfo.columnName;
	}

	public Field getIdField() {
		return idInfo.field;
	}

	private class IdInfo {
		private String columnName;
		private Field field;

		IdInfo(String columnName, Field field) {
			this.columnName = columnName;
			this.field = field;
		}
	}

}
