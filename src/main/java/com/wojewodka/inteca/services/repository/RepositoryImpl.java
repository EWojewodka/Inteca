package com.wojewodka.inteca.services.repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojewodka.inteca.services.database.DBAWrapper;
import com.wojewodka.inteca.services.database.DatabaseFieldProcessor;
import com.wojewodka.inteca.services.dbo.AnnotationManager;
import com.wojewodka.inteca.services.dbo.DatabaseObject;
import com.wojewodka.inteca.services.dbo.EntityMetadata;
import com.wojewodka.inteca.services.logging.LoggerFactory;
import com.wojewodka.inteca.utils.EntityUtils;

@Service
public class RepositoryImpl<T extends DatabaseObject> implements Repository<T> {

	@Autowired
	private DBAWrapper wrapper;

	private static final Logger LOGGER = LoggerFactory.getLogger();

	@Override
	public void save(final T entity) throws Exception {
		if (EntityUtils.getId(entity) >= 0)
			updateEntity(entity);
		else
			insertEntity(entity);
	}

	@Override
	public void delete(T entity) throws Exception {
	}

	@Override
	public T findById(int id) {
		return null;
	}

	@Override
	public List<T> find(RepositorySearch repositorySearch) {
		return null;
	}

	/**
	 * Create update query and execute in transaction.
	 * 
	 * @param entity
	 * @throws Exception
	 */
	private void updateEntity(final T entity) throws Exception {
	}

	private void insertEntity(final T entity) throws Exception {
		EntityMetadata metadata = AnnotationManager.getInstance().getInfo(entity.getClass());
		Map<String, Field> columns = metadata.getColumns();
		StringBuilder columnSb = new StringBuilder();
		StringBuilder valuesSb = new StringBuilder();
		for (Entry<String, Field> en : columns.entrySet()) {
			columnSb.append(en.getKey() + ",");
			valuesSb.append(DatabaseFieldProcessor.process(en.getValue().get(entity)) + ",");
		}
		columnSb.setLength(columnSb.length() - 1);
		valuesSb.setLength(valuesSb.length() - 1);
		wrapper.run(con -> {
			
			String q = "INSERT INTO " + metadata.getTableName() + " VALUES(";
			return null;
		});
	}

}
