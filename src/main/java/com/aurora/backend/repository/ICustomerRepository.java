package com.aurora.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.backend.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, UUID> {
	
	public List<Customer> findAllByFullnameContainingIgnoreCase(String name);
	
	

}
