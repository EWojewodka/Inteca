package com.wojewodka.inteca.services.repository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojewodka.inteca.services.database.DBAWrapper;
import com.wojewodka.inteca.services.database.DatabaseFieldProcessor;
import com.wojewodka.inteca.services.dbo.AnnotationManager;
import com.wojewodka.inteca.services.dbo.DatabaseObject;
import com.wojewodka.inteca.services.dbo.EntityMetadata;
import com.wojewodka.inteca.services.dbo.annotations.Id;
import com.wojewodka.inteca.services.logging.LoggerFactory;
import com.wojewodka.inteca.utils.CollectionUtils;
import com.wojewodka.inteca.utils.EntityUtils;
import com.wojewodka.inteca.utils.StringUtils;

@Repository
public class RepositoryImpl<T extends DatabaseObject>
		implements com.wojewodka.inteca.services.repository.Repository<T> {

	@Autowired
	private DBAWrapper wrapper;

	private static final Logger LOGGER = LoggerFactory.getLogger();

	public void save(T entity, Connection connection) throws Exception {
		if (EntityUtils.getId(entity) > 0)
			updateEntity(entity, connection);
		else
			insertEntity(entity, connection);
	}

	@Override
	public void save(T entity) throws Exception {
		wrapper.run(con -> {
			save(entity, con);
			return null;
		});
	}

	@Override
	public void delete(T entity) throws Exception {
		wrapper.run(con -> {
			delete(entity, con);
			return null;
		});
	}

	@Override
	public void delete(T entity, Connection connection) throws Exception {
		if (entity == null) {
			LOGGER.info("Why you try delete nullable entity?!");
			return;
		}
		EntityMetadata metadata = AnnotationManager.getInstance().getInfo(entity.getClass());
		String query = "DELETE FROM " + metadata.getTableName() + " WHERE " + metadata.getIdColumnName() + " = "
				+ entity.getId() + ";";
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query);
	}

	@Override
	public T findById(int id) {
		RepositorySearch rs = new RepositorySearch();
		EntityMetadata genericType = getMetadata();
		rs.where(genericType.getIdColumnName(), id);
		List<T> result = find(rs);
		return result.isEmpty() ? null : result.get(0);
	}

	/**
	 * @return
	 */
	private EntityMetadata getMetadata() {
		return AnnotationManager.getInstance().getInfo(getGenericType());
	}

	/**
	 * @return
	 */
	private Class<?> getGenericType() {
		return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// TODO: it's not pretty code, here I should use some tokens or smth like that
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(RepositorySearch repositorySearch) {
		EntityMetadata metadata = getMetadata();
		String distinct = repositorySearch.isDistinct() ? "DISTINCT" : "";
		// create smth like SELECT DISTINCT[if set] * FROM inteca_families and set main table alias
		String alias = repositorySearch.getFromTableAlias();
		if (StringUtils.isEmpty(alias))
			alias = "t1";
		StringBuilder sb = new StringBuilder(
				"SELECT " + distinct + " " + alias + ".* FROM " + metadata.getTableName() + " AS " + alias);

		// add joiners
		List<SearchJoiner> joiners = repositorySearch.getJoiners();
		if (!joiners.isEmpty()) {
			for (SearchJoiner joiner : joiners) {
				sb.append(" " + joiner.getType().getSqlName() +" " +joiner.getRightTable() + " AS " + joiner.getAlias() + " ON "
						+ joiner.getCondition());
			}
		}

		Map<String, Object> whereClause = repositorySearch.getWhereClause();

		// Check and add where cluase.
		boolean useWhere = !whereClause.isEmpty();
		if (useWhere) {
			sb.append(" WHERE ");
			for (String fieldName : whereClause.keySet()) {
				fieldName = fieldName.contains(".") ? fieldName : alias + "." + fieldName;
				sb.append(fieldName + " =? AND ");
			}
			sb.setLength(sb.length() - 4);
		}

		//set order by column and order direction
		String orderColumn = repositorySearch.getOrderColumn();
		OrderByDirection orderByDirection = repositorySearch.getOrderBy();
		if (!StringUtils.isEmpty(orderColumn) && orderByDirection != null) {
			sb.append(" ORDER BY " + orderColumn + " " + orderByDirection.getCode());
		}

		//set limit
		int limit = repositorySearch.getLimit();
		if (limit > 0) {
			sb.append(" LIMIT " + limit);
		}
		sb.append(";");

		return wrapper.run(con -> {
			// We have to replace ? with values if where clause is not empty
			PreparedStatement stmt = con.prepareStatement(sb.toString());
			LOGGER.info(sb.toString());
			if (useWhere) {
				int i = 1;
				for (Object obj : whereClause.values()) {
					stmt.setObject(i, obj, DatabaseFieldProcessor.getSqlType(obj).getVendorTypeNumber());
					i++;
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData mt = rs.getMetaData();
			int columnCount = mt.getColumnCount();
			List<T> result = new LinkedList<>();
			//create java object from result set
			while(rs.next()) {
				Class<?> genericType = getGenericType();
				T instance = (T) genericType.newInstance();
				for (int i = 1; i < columnCount + 1; i++) {
					Field field = metadata.getFieldForColumn(mt.getColumnName(i));
					if (field == null)
						continue;
					field.set(instance, rs.getObject(i));
				}
				result.add(instance);
			}
			return result;
		});

	}
	
	/**
	 * Create update query and execute in transaction.
	 * 
	 * @param entity
	 * @throws Exception
	 */
	private void updateEntity(T entity, Connection connection) throws Exception {
		EntityMetadata metadata = getMetadata();
		Map<String, Field> columns = metadata.getColumns();

		StringBuilder sb = new StringBuilder("UPDATE " + metadata.getTableName() + " SET ");
		columns.forEach((k, v) -> {
			sb.append(k + "=? ,");
		});
		sb.setLength(sb.length() - 1);

		sb.append(" WHERE " + metadata.getIdColumnName() + " = " + entity.getId() + ";");
		PreparedStatement stmt = connection.prepareStatement(sb.toString());
		stmt.executeUpdate();
	}

	/**
	 * Insert entity and set value of returned id into annotated field by {@link Id}
	 * in object.
	 * 
	 * @param entity
	 * @throws Exception
	 */
	private void insertEntity(T entity, Connection connection) throws Exception {
		EntityMetadata metadata = getMetadata();
		Map<String, Field> columns = metadata.getColumns();
		String fieldsToInsert = CollectionUtils.toGenericString(columns.keySet(), ",");
		// Create insert string and put there fields names
		StringBuilder sb = new StringBuilder(
				"INSERT INTO " + metadata.getTableName() + "(" + fieldsToInsert + ") VALUES (");

		// Add ? for jdbc statement
		for (int i = 0; i < columns.size(); i++) {
			sb.append("?,");
		}
		sb.setLength(sb.length() - 1);
		sb.append(");");
		String prepareQuery = sb.toString();
		// Prepare statement with query and order to return a generated primary key
		PreparedStatement stmt = connection.prepareStatement(prepareQuery, Statement.RETURN_GENERATED_KEYS);
		LOGGER.info("Executed query: " + prepareQuery);
		fillStatementByValues(entity, columns, stmt);
		stmt.executeUpdate();

		// Set id in entity
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			int id = rs.getInt(1);
			metadata.getIdField().set(entity, id);
		}
	}

	/**
	 * @param entity
	 * @param columns
	 * @param stmt
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	private void fillStatementByValues(T entity, Map<String, Field> columns, PreparedStatement stmt)
			throws IllegalAccessException, SQLException {
		int i = 1;
		for (Field field : columns.values()) {
			Object value = field.get(entity);
			// Replace ? with value
			stmt.setObject(i++, value, DatabaseFieldProcessor.getSqlType(value).getVendorTypeNumber());
		}
	}

}
