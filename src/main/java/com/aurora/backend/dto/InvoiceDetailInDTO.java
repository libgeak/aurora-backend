package com.aurora.backend.dto;

import java.util.UUID;
import com.aurora.backend.model.Invoice;
import com.aurora.backend.model.Product;

import lombok.Data;

@Data
public class InvoiceDetailInDTO implements ICustomDTO {

	private UUID id;
	
	private ProductInDTO product;
	
	private InvoiceInDTO invoice;
	
	private double amount;
	
	private double quality;
	
	private double subtotal;
	
	private String codeUnit; 
}
