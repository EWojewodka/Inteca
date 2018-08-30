package com.wojewodka.inteca.services.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public enum LoggerFactory {
	INSTANCE;

	private ILoggerFactory factory = org.slf4j.LoggerFactory.getILoggerFactory();

	private LoggerFactory() {
	}

	private static LoggerFactory getInstance() {
		return LoggerFactory.INSTANCE;
	}

	/**
	 * Return standard logger.
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		return getInstance().factory.getLogger(clazz.getName());
	}

	/**
	 * Simpler version of {@link #getLogger(Class)} </br>
	 * Returned logger is linked with invoking class.
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		return getLogger(3);
	}

	/**
	 * Get logger with name of backTo class.
	 * 
	 * @param backTo
	 *            - define how many steps back class of current thread stack trace
	 *            should invoke logger.
	 * @return
	 */
	public static Logger getLogger(int backTo) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return org.slf4j.LoggerFactory.getLogger(stackTrace[backTo].getClassName());
	}

}
