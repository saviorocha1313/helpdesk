package com.savio.helpdesk.services.exceptions;

public class ObjectnotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;// Classe para tratamento de exceções 

	public ObjectnotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectnotFoundException(String message) {
		super(message);
	}
	

}
