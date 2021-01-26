package com.aurora.backend.dto;

import java.util.Date;
import java.util.UUID;

import com.aurora.backend.model.Unit;

import lombok.Data;

@Data
public class ProductOutDTO implements ICustomDTO {
	
	private UUID id;
	
	private String name;
	
	private double unitValue;
	
	private Date updatedAt;
	
	private UnitOutDTO unit;
	
	private String state;
}
