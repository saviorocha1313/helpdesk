package com.savio.helpdesk.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;// Classe para tratamento de exceções 

	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
	

}
