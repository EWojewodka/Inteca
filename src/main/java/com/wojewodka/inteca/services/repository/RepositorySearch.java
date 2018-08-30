package com.wojewodka.inteca.services.repository;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositorySearch {

	// Reason for use linked hash map is same as in EntityMetadata - easier and more
	// efficienty for this
	private Map<String, Object> whereClause = new LinkedHashMap<>();

	private boolean distinct = false;

	private int limit = -1;

	private OrderByDirection orderBy;

	private String orderColumn;

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public OrderByDirection getOrderBy() {
		return orderBy;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderBy(String orderColumn, OrderByDirection orderBy) {
		this.orderBy = orderBy;
		this.orderColumn = orderColumn;
	}

	public Map<String, Object> getWhereClause() {
		return whereClause;
	}

	public void where(String columnName, Object whereObject) {
		whereClause.put(columnName, whereObject);
	}

}
