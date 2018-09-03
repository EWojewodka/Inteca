package com.wojewodka.inteca.common;

public class AjaxException extends RuntimeException {

	private static final long serialVersionUID = 5929755942846638650L;

	private int httpCode;

	public AjaxException(String message, int httpCode) {
		super(message);
		this.httpCode = httpCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

}
