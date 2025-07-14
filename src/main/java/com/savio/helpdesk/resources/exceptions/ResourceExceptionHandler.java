package com.savio.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.savio.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.savio.helpdesk.services.exceptions.ObjectnotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler { //manipulador de exceções do nosso recurso
	
	@ExceptionHandler(ObjectnotFoundException.class) ///Aqui estou manipulando a classe
	public ResponseEntity<StandardError> objectnotFoundException(ObjectnotFoundException ex,
			HttpServletRequest request){
		
		StandardError error = new  StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),//Aqui foi criado um manipulador de exceções para object not found
				"Object Not Found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(DataIntegrityViolationException.class) ///Aqui estou manipulando a classe
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request){
		
		StandardError error = new  StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),//Aqui foi criado um manipulador de exceções para object not found
				"Violação de Dados", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
