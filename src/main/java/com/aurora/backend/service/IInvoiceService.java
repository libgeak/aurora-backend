package com.aurora.backend.service;

import java.util.List;
import java.util.UUID;

import com.aurora.backend.model.Customer;
import com.aurora.backend.model.Invoice;
import com.aurora.backend.model.InvoiceDetail;

public interface IInvoiceService {
	
	public Invoice save(Invoice invoice);
	
	public Invoice saveAll(Invoice invoice, List<InvoiceDetail> invoiceDetail);
	
	public List<Invoice> findAllByCustomerById(UUID id);



}
