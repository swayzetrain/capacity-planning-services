package tech.swayzetrain.capacity.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tech.swayzetrain.capacity.common.enums.ErrorLevel;
import tech.swayzetrain.capacity.common.exception.InvalidRequestParameterException;
import tech.swayzetrain.capacity.common.exception.InvalidUUIDException;
import tech.swayzetrain.capacity.common.exception.ObjectNotFoundException;
import tech.swayzetrain.capacity.common.model.ErrorResponse;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler({ ObjectNotFoundException.class } )
	public ResponseEntity<ErrorResponse> handleObjectNotFoundException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ErrorLevel.WARN), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ InvalidUUIDException.class, InvalidRequestParameterException.class} )
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ErrorLevel.WARN), HttpStatus.BAD_REQUEST);
	}

}
