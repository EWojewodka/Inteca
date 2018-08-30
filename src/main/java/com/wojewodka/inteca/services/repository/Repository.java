package com.wojewodka.inteca.services.repository;

import java.util.List;

import com.wojewodka.inteca.services.dbo.DatabaseObject;

public interface Repository<T extends DatabaseObject> {

	void save(T entity) throws Exception;

	void delete(T entity) throws Exception;

	T findById(int id);

	List<T> find(RepositorySearch repositorySearch);
	
}
