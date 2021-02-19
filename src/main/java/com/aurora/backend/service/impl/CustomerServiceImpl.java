package com.aurora.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.backend.model.Customer;
import com.aurora.backend.repository.ICustomerRepository;
import com.aurora.backend.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(UUID id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public List<Customer> findAllByParams(String name) {		
		return customerRepository.findAllByFullnameContainingIgnoreCase(name);
	}

}
