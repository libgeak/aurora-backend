package com.aurora.backend.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.backend.dto.InvoiceDetailInDTO;
import com.aurora.backend.dto.InvoiceInDTO;
import com.aurora.backend.dto.WrapperInvoiceInDTO;
import com.aurora.backend.enums.CustomExceptionKeyEnum;
import com.aurora.backend.model.Invoice;
import com.aurora.backend.model.InvoiceDetail;
import com.aurora.backend.service.IInvoiceDetailService;
import com.aurora.backend.service.IInvoiceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("invoices")
public class InvoiceRestController  extends CustomAbstractRestController{
	
	private Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
	private IInvoiceDetailService invoiceDetailService; 

	@Autowired
	private IInvoiceService invoiceService;
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody WrapperInvoiceInDTO  wrapperInvoiceDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			Invoice invoice = this.convertToEntity(wrapperInvoiceDTO.getInvoice());
			
			List<InvoiceDetail> invoiceDetails = 
					 wrapperInvoiceDTO
						.getInvoiceDetails()
						.stream()
						.map(this::convertToEntity)
						.collect(Collectors.toList());
			
			invoiceService.saveAll(invoice, invoiceDetails);		
			
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}
 
	}
	
	
	@GetMapping("/listInvoiceByCustomer/{id}")
	public ResponseEntity<?> create(@PathVariable("id") UUID id) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			
			List<InvoiceInDTO> invoicesDTO = 
					invoiceService
						.findAllByCustomerById(id)
						.stream()
						.map(this::convertToDto)
						.collect(Collectors.toList()); 
					 
			
			this.responseAddObject(mapResponse, invoicesDTO);
			
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}
 
	}
	
	@GetMapping("/listDetailsInvoice/{idInvoice}")
	public ResponseEntity<?> listDetailsInvoice(@PathVariable("idInvoice") UUID id) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			
			List<InvoiceDetailInDTO> invoicesDTO = 
					invoiceDetailService
						.findAll(id)
						.stream()
						.map(this::convertToDto)
						.collect(Collectors.toList()); 
					 
			
			this.responseAddObject(mapResponse, invoicesDTO);
			
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}
 
	}
	
	@GetMapping("/sumTotalInvoice")
	public ResponseEntity<?> sumTotalInvoice() {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
			;
			
			Double total = invoiceService.sumTotalInvoicesByDate(simpleDate.parse(LocalDate.now().toString()));
			if(total == null) {
				total = Double.valueOf(0);
			}
					 
			mapResponse.put(CustomExceptionKeyEnum.RESULT.toString(), total);						
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}
 
	}
	
	private InvoiceInDTO convertToDto(Invoice invoice) {
		return modelMapper.map(invoice, InvoiceInDTO.class);
	}
	
	private InvoiceDetailInDTO convertToDto(InvoiceDetail invoiceDetail) {
		return modelMapper.map(invoiceDetail, InvoiceDetailInDTO.class);
	}
	
	private Invoice convertToEntity(InvoiceInDTO dto) {
		Invoice invoice  = modelMapper.map(dto, Invoice.class);
		return invoice;
	}
	
	private InvoiceDetail convertToEntity(InvoiceDetailInDTO dto) {
		InvoiceDetail invoiceDetail  = modelMapper.map(dto, InvoiceDetail.class);
		return invoiceDetail;
	}
}
