/**
 * 
 */
package com.thiyagu.todoservice.exception;

/**
 * @author Thiyagu
 *
 */
public class TodoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * status code for this error
	 */
	public int statusCode;
	/**
	 * The reason for this exception
	 * 
	 */
	public String errorCause;

	/**
	 * Constructor for TodoExisitsException
	 * 
	 * @param throwable
	 * @param message
	 * @param errorCode
	 * @param errorCause
	 */	
	public TodoException(Throwable throwable, String message, int errorCode, String errorCause) {
		super(message, throwable);
		this.statusCode = errorCode;
		this.errorCause = errorCause;
	}
	
	public TodoException(int errorCode, String errorCause ) {
		super(errorCause);
		this.statusCode = errorCode;
		this.errorCause = errorCause;
	}
}
