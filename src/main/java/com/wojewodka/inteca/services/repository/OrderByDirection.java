package com.wojewodka.inteca.services.repository;

public enum OrderByDirection {

	ASCENDING("ASC"),
	/**/
	DESCENDING("DESC");

	private String code;

	private OrderByDirection(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
