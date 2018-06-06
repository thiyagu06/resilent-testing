/**
 * 
 */
package com.thiyagu.todoservice.exception.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.thiyagu.todoservice.exception.TodoException;

/**
 * Exception handler for TodoException
 * 
 * @author Thiyagu
 *
 */
@RestControllerAdvice
public class TodoExceptionHandler {

	/**
	 * Exception handler method for TodoException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(TodoException.class)
	public ResponseEntity<Object> handleTodoException(TodoException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
