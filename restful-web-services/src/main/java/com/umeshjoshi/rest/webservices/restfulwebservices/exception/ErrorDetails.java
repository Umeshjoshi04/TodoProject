package com.umeshjoshi.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String details;
	private String statusCode;
	public ErrorDetails(LocalDateTime now, String message2, String description, String statusCode) {
		this.timeStamp = now;
		this.message = message2;
		this.details = description;
		this.statusCode = statusCode;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public String getStatusCode() {
		return statusCode;
	}
}
