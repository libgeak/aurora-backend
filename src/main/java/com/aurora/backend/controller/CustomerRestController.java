package com.aurora.backend.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.backend.dto.CustomerInDTO;
import com.aurora.backend.model.Customer;
import com.aurora.backend.service.ICustomerService;
import com.aurora.backend.service.IUnitService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("customers")
public class CustomerRestController extends CustomAbstractRestController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	private ICustomerService customerService; 
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody CustomerInDTO customerDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			Customer customer = this.convertToEntity(customerDTO);
			customer = customerService.save(customer);
			
			this.responseAddObject(mapResponse, this.convertToDto(customer));
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}

	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> getcustomer(@PathVariable("id") UUID id){
		Map<String, Object> mapResponse = new HashMap<String, Object>();
		try {
			Customer customer = customerService.findById(id);
			
			this.responseAddObject(mapResponse, this.convertToDto(customer));
			return this.responseReturnOK(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}

	}
	
	@PutMapping("/create")
	public ResponseEntity<?> updatecustomer(@RequestBody CustomerInDTO customerDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			Customer customer = this.convertToEntity(customerDTO);
			customer = customerService.save(customer);
			
			this.responseAddObject(mapResponse, this.convertToDto(customer));
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listcustomers() {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			List<CustomerInDTO> listUnits =  customerService
					.findAll()
					.stream()
					.map(this::convertToDto)
					.collect(Collectors.toList()); 	
			
			
			this.responseAddObject(mapResponse, listUnits);
			return this.responseReturnOK(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}

	}
	
	@GetMapping("/searchByParam/{name}")
	public ResponseEntity<?> getcustomer(@PathVariable("name") String name){
		Map<String, Object> mapResponse = new HashMap<String, Object>();
		try {
			List<CustomerInDTO> customers = 
					customerService.findAllByParams(name)
					.stream()
					.map(this::convertToDto)
					.collect(Collectors.toList());
			
			this.responseAddObject(mapResponse, customers);
			return this.responseReturnOK(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}

	}
	
	private CustomerInDTO convertToDto(Customer customer) {
		return modelMapper.map(customer, CustomerInDTO.class);
	}
	
	private Customer convertToEntity(CustomerInDTO dto) {
		Customer customer = modelMapper.map(dto, Customer.class);
		
		return customer;
	}
	
	

}
