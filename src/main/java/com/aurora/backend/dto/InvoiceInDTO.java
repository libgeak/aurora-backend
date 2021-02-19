package com.aurora.backend.dto;

import java.util.Date;
import java.util.UUID;

import com.aurora.backend.model.Customer;

import lombok.Data;

@Data
public class InvoiceInDTO implements ICustomDTO  {
	
	private UUID id;	
	
	private Date createdAt;
		
	private double total;
	
	private CustomerInDTO customer;
}
