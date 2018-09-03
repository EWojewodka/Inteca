package com.wojewodka.inteca.services;

import com.wojewodka.inteca.common.AjaxException;

public class ExceptionModel {

	private int httpCode;

	private String message;

	public ExceptionModel(Throwable t) {
		this(t.getMessage(), (t instanceof AjaxException) ? ((AjaxException) t).getHttpCode() : 500);
	}

	public ExceptionModel(String message, int httpCode) {
		this.message = message;
		this.httpCode = httpCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExceptionModel [httpCode=" + httpCode + ", message=" + message + "]";
	}

}
