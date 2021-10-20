package com.recipe.util;

import java.time.LocalDateTime;

/**
 * This class for API ExceptionResponse data members
 * @author saemmadi
 *
 */
public class ErrorResponse {

	private String message;
	private LocalDateTime timestamp;
	private int statusCode;

	/**
	 * @param message
	 * @param timestamp
	 */
	public ErrorResponse(String message, LocalDateTime timestamp, int statusCode) {
		this.message = message;
		this.timestamp = timestamp;
		this.statusCode = statusCode;
	}

	/**
	 * 
	 */
	public ErrorResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
