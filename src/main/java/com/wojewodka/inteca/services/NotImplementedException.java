/**
 * 
 */
package com.wojewodka.inteca.services;

/**
 * @author Emil Wojewódka
 *
 * @since 30 sie 2018
 */
public class NotImplementedException extends RuntimeException {

	private static final long serialVersionUID = -7886991748868619593L;

	public NotImplementedException(String message) {
		super("Not implemented: " + message);
	}

	public NotImplementedException() {
		this("");
	}

}
