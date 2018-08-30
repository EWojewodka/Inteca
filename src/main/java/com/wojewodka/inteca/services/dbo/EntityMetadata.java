package com.wojewodka.inteca.services.dbo;

import java.lang.reflect.Field;
import java.util.HashMap;
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

	private Map<String, Field> annotatedEntityFields = new HashMap<>();

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

	public String getTableName() {
		return tableName;
	}

	public Map<String, Field> getColumns() {
		return annotatedEntityFields;
	}

	public Field getFieldForColumn(String columnName) {
		return annotatedEntityFields.get(columnName);
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
