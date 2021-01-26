package com.aurora.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.aurora.backend.enums.MessageEncoderEnum;

@Component
public class MessageGeneratorConfig {
	
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String messageCode, Object... arguments) {
		return messageSource.getMessage(messageCode, arguments, LocaleContextHolder.getLocale());
	}
	
	public String getMessage(MessageEncoderEnum messageCode, Object... arguments) {
		return messageSource.getMessage(messageCode.getCode(), arguments, LocaleContextHolder.getLocale());
	}
}
