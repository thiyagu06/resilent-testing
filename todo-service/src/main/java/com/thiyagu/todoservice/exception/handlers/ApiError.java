/**
 * 
 */
package com.thiyagu.todoservice.exception.handlers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.ToString;

/**
 * @author Thiyagu
 *
 */
@Data
@ToString
public class ApiError {

	/**
	 * The http status
	 */
	private HttpStatus status;
	
	/**
	 * The message
	 */
	private String message;
	
	/**
	 * The list of errors
	 */
	private List<String> errors;

	public ApiError() {
		super();
	}

	/**
	 * @param status
	 * @param message
	 * @param errors
	 */
	public ApiError(final HttpStatus status, final String message, final List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	/**
	 * @param status
	 * @param message
	 * @param error
	 */
	public ApiError(final HttpStatus status, final String message, final String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

}
