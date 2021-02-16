package com.aurora.backend.service;

import java.util.List;
import java.util.UUID;

import com.aurora.backend.model.Customer;

public interface ICustomerService {
	
	public List<Customer> findAll();
	
	public Customer save(Customer customer);
	
	public Customer findById(UUID id);
	
	public List<Customer> findAllByParams(String name);
	

}
