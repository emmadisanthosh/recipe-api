package com.recipe.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * All sort of Exceptions will be handled in ExceptionHandler class
 * 
 * @author saemmadi
 *
 */
@ControllerAdvice
public class ExceptionHanlder {

	/**
	 * Returns Custom Exception information along with status when a record is not
	 * found
	 * 
	 * @param exception - message related to exception
	 * @param request   - URL details
	 * @return - custom exception message along with status NOT_FOUND
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> recordNotFound(RecordNotFoundException exception, WebRequest request) {
		ErrorResponse error = new ErrorResponse("No Records Found in Database for RecipeId", LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Returns Exception information along with status
	 * 
	 * @param exception message related to exception
	 * @return custom exception message along with status BAD_REQUEST
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleCustomValidations(MethodArgumentNotValidException exception) {
		ErrorResponse error = new ErrorResponse(exception.getBindingResult().getFieldError().getDefaultMessage(),
				LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param exception - detailed message related to exception
	 * @param request   - request url details
	 * @return - custom exception details along with status INTERNAL_SERVER_ERROR
	 */

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExcpetion(Exception exception, WebRequest request) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
