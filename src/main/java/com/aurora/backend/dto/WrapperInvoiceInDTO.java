package com.aurora.backend.dto;


import java.util.List;

import lombok.Data;

@Data
public class WrapperInvoiceInDTO implements ICustomDTO {
	
	private InvoiceInDTO invoice;
	
	private List<InvoiceDetailInDTO> invoiceDetails;

}
