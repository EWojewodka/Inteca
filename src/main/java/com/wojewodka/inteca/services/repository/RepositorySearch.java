package com.wojewodka.inteca.services.repository;

import java.util.HashMap;
import java.util.Map;

public class RepositorySearch {

	private Map<String, Object> whereClause = new HashMap<>();

	private boolean distinct = false;

	private int limit = -1;

	private OrderByDirection orderBy;

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

	public void setOrderBy(OrderByDirection orderBy) {
		this.orderBy = orderBy;
	}

	public Map<String, Object> getWhereClause() {
		return whereClause;
	}

	public void where(String columnName, Object whereObject) {
		whereClause.put(columnName, whereObject);
	}

}
