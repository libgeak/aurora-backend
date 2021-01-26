package com.aurora.backend.enums;

public enum MessageEncoderEnum {
	GENERAL_EXCEPTION ("GENERAL_ERROR"),
	GENERAL_SUCCESS ("GENERAL_SUCCESS"),
	FILE_UPLOAD_LOADED ("GENERAL_FILE_UPLOAD_VALIDATE_SUCCESS"),
	UNIT_VALID_UNIQUE_CODE("UNIT_001");

private String code;
	
	private MessageEncoderEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
