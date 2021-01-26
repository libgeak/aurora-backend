package com.aurora.backend.exception;

import com.aurora.backend.enums.CustomExceptionEnum;

public class CustomException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private Throwable exception;
	private String userMessage;
	private String errorCode;
	private CustomExceptionEnum customExceptionEnum;

	public CustomException(CustomExceptionEnum customExceptionEnum, String userMessage) {
		this.customExceptionEnum = customExceptionEnum;
		this.userMessage = userMessage;
	}

	public CustomException(CustomExceptionEnum customExceptionEnum, String userMessage, String errorCode) {
		this.customExceptionEnum = customExceptionEnum;
		this.userMessage = userMessage;
		this.errorCode = errorCode;
	}
	
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public CustomExceptionEnum getCustomExceptionEnum() {
		return customExceptionEnum;
	}
	public void setCustomExceptionEnum(CustomExceptionEnum customExceptionEnum) {
		this.customExceptionEnum = customExceptionEnum;
	}
}
