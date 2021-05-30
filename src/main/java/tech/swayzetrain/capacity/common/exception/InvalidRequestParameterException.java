package tech.swayzetrain.capacity.common.exception;

public class InvalidRequestParameterException extends RuntimeException{

	private static final long serialVersionUID = -7163407724852911713L;

	public InvalidRequestParameterException(String message) {
		super(message);
	}
}
