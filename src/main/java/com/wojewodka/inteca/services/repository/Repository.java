package com.wojewodka.inteca.services.repository;

import java.sql.Connection;
import java.util.List;

import com.wojewodka.inteca.services.dbo.DatabaseObject;

public interface Repository<T extends DatabaseObject> {

	void save(T entity) throws Exception;

	void save(T entity, Connection connection) throws Exception;

	void delete(T entity) throws Exception;

	void delete(T entity, Connection connection) throws Exception;

	T findById(int id);

	List<T> find(RepositorySearch repositorySearch);

}
