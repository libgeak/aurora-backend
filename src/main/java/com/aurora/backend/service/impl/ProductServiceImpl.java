package com.aurora.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.backend.model.Customer;
import com.aurora.backend.model.Product;
import com.aurora.backend.repository.IProductRepository;
import com.aurora.backend.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product unit) {
		return productRepository.save(unit);
	}

	@Override
	public Product findById(UUID id) {		
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Product> findAllByParams(String name) {		
		return productRepository.findAllByNameContainingIgnoreCase(name);
	}

}
