package com.aurora.backend.service;

import java.util.List;
import java.util.UUID;

import com.aurora.backend.model.Customer;
import com.aurora.backend.model.Product;

public interface IProductService {
	
	public List<Product> findAll();
	
	public Product save(Product unit);
	
	public Product findById(UUID id);
	
	public List<Product> findAllByParams(String name);

}
