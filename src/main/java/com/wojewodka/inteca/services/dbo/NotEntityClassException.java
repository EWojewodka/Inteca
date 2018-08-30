package com.wojewodka.inteca.services.dbo;

public class NotEntityClassException extends RuntimeException {

	private static final long serialVersionUID = -8652099801079112407L;

	public NotEntityClassException(Class<?> clazz) {
		super("Cannot analyze class [" + clazz.getName() + "] for searching metadata. It's not entity class.");
	}

}
