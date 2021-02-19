package com.aurora.backend.service.impl;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.backend.model.InvoiceDetail;
import com.aurora.backend.repository.IInvoiceDetailRepository;
import com.aurora.backend.service.IInvoiceDetailService;
@Service
public class InvoiceDetailServiceImpl implements IInvoiceDetailService{
	
	@Autowired
	private IInvoiceDetailRepository invoiceDetailRepository;
	
	@Override
	public List<InvoiceDetail> saveAll(List<InvoiceDetail> invoiceDetails){
		return invoiceDetailRepository.saveAll(invoiceDetails);
		
	}

	@Override
	public List<InvoiceDetail> findAll(UUID id) {		
		return this.invoiceDetailRepository.findAllByInvoiceId(id);
	}

}
