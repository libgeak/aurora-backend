package com.aurora.backend.enums;

public enum StateUnitEnum {
	ACTIVE("A"),
	INACTIVE("I");
	
	private String code;
	
	private StateUnitEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	

}
