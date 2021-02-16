package com.aurora.backend.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CustomerInDTO implements ICustomDTO{
	
private UUID id;
	
	private String identificationNumber;
	
	private String fullname;
	
	private String email;
	
	private String telephone;
	
	private String direction;
	
	private String city;
	
	private String department;
	
	private String observations;


}
