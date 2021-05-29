package tech.swayzetrain.capacity.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.swayzetrain.capacity.common.enums.ErrorLevel;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
	
	private String developerMessage;
	private ErrorLevel errorLevel;
	
	@JsonIgnore
	private Throwable exception;
	
	public ErrorResponse(String developerMessage, ErrorLevel errorLevel) {
		this.developerMessage = developerMessage;
		this.errorLevel = errorLevel;
	}
	
	public ErrorResponse(String developerMessage, ErrorLevel errorLevel, Throwable exception) {
		this.developerMessage = developerMessage;
		this.errorLevel = errorLevel;
		this.exception = exception;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}

	public Throwable getException() {
		return exception;
	}

}