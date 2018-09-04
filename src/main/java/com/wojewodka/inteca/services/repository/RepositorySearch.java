package com.wojewodka.inteca.services.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.util.ObjectUtils;

public class RepositorySearch {

	// Reason for use linked hash map is same as in EntityMetadata - easier and more
	// efficienty for this
	private Map<String, Object> whereClause = new LinkedHashMap<>();

	private boolean distinct = false;

	private int limit = -1;

	private OrderByDirection orderBy;

	private String orderColumn;

	private List<SearchJoiner> joiners = new LinkedList<>();

	private String fromTableAlias;

	public boolean isDistinct() {
		return distinct;
	}

	public RepositorySearch setFromTableAlias(String fromTableAlias) {
		this.fromTableAlias = fromTableAlias;
		return this;
	}

	public String getFromTableAlias() {
		return fromTableAlias;
	}

	public RepositorySearch setDistinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public RepositorySearch setLimit(int limit) {
		this.limit = limit;
		return this;
	}

	public OrderByDirection getOrderBy() {
		return orderBy;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public RepositorySearch setOrderBy(String orderColumn, OrderByDirection orderBy) {
		this.orderBy = orderBy;
		this.orderColumn = orderColumn;
		return this;
	}

	public Map<String, Object> getWhereClause() {
		return whereClause;
	}

	public RepositorySearch where(String columnName, Object whereObject) {
		whereClause.put(columnName, whereObject);
		return this;
	}

	public RepositorySearch where(String columnName, Object whereObject, boolean skipIfNull) {
		if (skipIfNull && ObjectUtils.isEmpty(whereObject))
			return this;
		return where(columnName, whereObject);
	}

	public RepositorySearch join(SearchJoiner joiner) {
		joiners.add(joiner);
		return this;
	}

	public List<SearchJoiner> getJoiners() {
		return joiners;
	}

}
