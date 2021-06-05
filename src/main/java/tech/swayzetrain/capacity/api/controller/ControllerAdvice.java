package tech.swayzetrain.capacity.api.controller;

import java.util.ListIterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tech.swayzetrain.capacity.common.constants.ExceptionConstants;
import tech.swayzetrain.capacity.common.enums.ErrorLevel;
import tech.swayzetrain.capacity.common.exception.CapacityProcessingException;
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
	
	@ExceptionHandler({ InvalidUUIDException.class, InvalidRequestParameterException.class } )
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ErrorLevel.WARN), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class} )
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		StringBuilder sb = new StringBuilder();
		
		ListIterator<ObjectError> iterator = ex.getBindingResult().getAllErrors().listIterator();
		
		while(iterator.hasNext()) {
			sb.append(iterator.next().getDefaultMessage());
			if(iterator.hasNext()) {
				sb.append(", ");
			} else {
				sb.append(".");
			}
		}
		
		return new ResponseEntity<>(new ErrorResponse(sb.toString(), ErrorLevel.WARN), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ HttpMessageNotReadableException.class} )
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ExceptionConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION_RESPONSE_MESSAGE, ErrorLevel.WARN), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ CapacityProcessingException.class} )
	public ResponseEntity<ErrorResponse> handleServiceProcessingException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ErrorLevel.WARN), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
