package com.aurora.backend.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.aurora.backend.enums.MessageEncoderEnum;
import com.aurora.backend.util.GenerateExceptionUtil;

public abstract class CustomComponentConfig {
	
	@Autowired
	protected MessageGeneratorConfig messageGenerator;
	
	protected MessageEncoderEnum enumMessageEncoder;
	
	@Autowired
	public GenerateExceptionUtil generateException;


}
