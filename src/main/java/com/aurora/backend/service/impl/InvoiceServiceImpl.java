package com.aurora.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurora.backend.model.Invoice;
import com.aurora.backend.model.InvoiceDetail;
import com.aurora.backend.repository.IInvoiceDetailRepository;
import com.aurora.backend.repository.IInvoiceRepository;
import com.aurora.backend.service.IInvoiceService;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
	
	@Autowired
	private IInvoiceRepository invoiceRepository;
	
	@Autowired
	private IInvoiceDetailRepository invoiceDetailRepository;
	
	
	
	@Override
	@Transactional
	public Invoice save(Invoice invoice) {
		return this.invoiceRepository.save(invoice);
	}


	@Override
	@Transactional
	public Invoice saveAll(Invoice invoice, List<InvoiceDetail> invoiceDetail) {
		Invoice newInvoice = invoiceRepository.save(invoice);
		
		List<InvoiceDetail> details = invoiceDetail
			.stream()
			.map(detail -> {
				detail.setInvoice(newInvoice);
				return detail;
			}).collect(Collectors.toList());
		
		invoiceDetailRepository.saveAll(details);
		return newInvoice;
	}


	@Override
	public List<Invoice> findAllByCustomerById(UUID id) {		
		return invoiceRepository.findAllByCustomerId(id);
	}


	@Override
	public Double sumTotalInvoicesByDate(Date date) {		
		
		return invoiceRepository.sumTotalInvoicesByDate(date);
	}
	
	

}
