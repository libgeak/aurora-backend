package com.aurora.backend.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductInDTO implements ICustomDTO {

	private UUID id;
	
	private String name;
	
	private double unitValue;
	
	private Date updatedAt;
	
	private Long idUnit;
	
	private String state;
}
