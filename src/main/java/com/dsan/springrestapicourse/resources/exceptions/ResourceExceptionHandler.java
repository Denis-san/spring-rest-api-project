package com.dsan.springrestapicourse.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dsan.springrestapicourse.services.exceptions.DataIntegrityException;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException err, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), err.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dateIntegrity(DataIntegrityException err, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), err.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException err, HttpServletRequest request) {

		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error!",
				System.currentTimeMillis());
		
		for(FieldError fieldError : err.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

}
