package com.aurora.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurora.backend.config.MessageGeneratorConfig;
import com.aurora.backend.enums.CustomExceptionEnum;
import com.aurora.backend.enums.MessageEncoderEnum;
import com.aurora.backend.exception.CustomException;
@Component
public class GenerateExceptionUtil extends RuntimeException {
	
	private Throwable exception;
	private String userMessage;
	private String errorCode;
	private CustomExceptionEnum customExceptionEnum;
	
	@Autowired
	private MessageGeneratorConfig msgGenerator;
	
	public void throwCustomException(CustomExceptionEnum customExceptionEnum, MessageEncoderEnum messageEncoder) {
		this.customExceptionEnum = customExceptionEnum;
		this.userMessage = this.msgGenerator.getMessage(messageEncoder) ;
		throw new CustomException(customExceptionEnum, this.userMessage);
	}
	
	public void throwCustomException(CustomExceptionEnum customExceptionEnum, MessageEncoderEnum messageEncoder, Object... params) {
		this.customExceptionEnum = customExceptionEnum;
		this.userMessage = this.msgGenerator.getMessage(messageEncoder, params) ;
		throw new CustomException(customExceptionEnum, this.userMessage);
	}
	
	public void throwCustomExceptionError(MessageEncoderEnum messageEncoder, Object... params) {
		this.throwCustomException(CustomExceptionEnum.ERROR, messageEncoder, params);
	}

	public void throwCustomExceptionError(MessageEncoderEnum messageEncoder) {
		this.throwCustomException(CustomExceptionEnum.ERROR, messageEncoder);
	}


}
