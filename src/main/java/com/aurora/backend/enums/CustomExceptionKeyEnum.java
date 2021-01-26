package com.aurora.backend.enums;

public enum CustomExceptionKeyEnum {
	RESULT("result"),
	MESSAGE_RESPONSE("messageResponse"),
	ERROR_RESPONSE("errorResponse")
;
	
	
	
	private String code;

	private CustomExceptionKeyEnum(String code) {
		this.code = code;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {		
		return this.code;
	}
	
	
	

}
