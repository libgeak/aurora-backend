package com.aurora.backend.service;

import java.util.List;
import java.util.UUID;

import com.aurora.backend.model.InvoiceDetail;

public interface IInvoiceDetailService {
	
	public List<InvoiceDetail> saveAll(List<InvoiceDetail> invoiceDetails);
	
	public List<InvoiceDetail> findAll(UUID id);


}
