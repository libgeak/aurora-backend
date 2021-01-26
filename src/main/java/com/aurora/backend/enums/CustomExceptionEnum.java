package com.aurora.backend.enums;

import java.util.HashMap;
import java.util.Map;

public enum CustomExceptionEnum {
	INFO ("INFO"),
	ERROR ("ERROR");
	
	private String value;
    private static Map<String, CustomExceptionEnum> mapEnum = new HashMap<>();

    private CustomExceptionEnum(String value) {
        this.value = value;
    }

    static {
        for (CustomExceptionEnum enumItem : CustomExceptionEnum.values()) {
        	mapEnum.put(enumItem.value, enumItem);
        }
    }

    public static CustomExceptionEnum getByValue(String valueEnum) {
        return (CustomExceptionEnum) mapEnum.get(valueEnum);
    }

    public String getValue() {
        return value;
    }
}
