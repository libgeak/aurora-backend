package com.aurora.backend.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class UnitOutDTO implements Serializable, ICustomDTO {
	
	private static final long serialVersionUID = 1L;

	private Long id; 
		
	private String code;
	
	private String name;
	
	private String state;

}
