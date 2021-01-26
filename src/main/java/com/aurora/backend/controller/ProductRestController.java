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

import com.aurora.backend.dto.ProductInDTO;
import com.aurora.backend.dto.ProductOutDTO;
import com.aurora.backend.model.Product;
import com.aurora.backend.service.IProductService;
import com.aurora.backend.service.IUnitService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("products")
public class ProductRestController extends CustomAbstractRestController {
	
	private Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
	private IProductService productService; 
	
	@Autowired
	private IUnitService unitService; 
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductInDTO productDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			Product product = this.convertToEntity(productDTO);
			product = productService.save(product);
			
			this.responseAddObject(mapResponse, this.convertToDto(product));
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}

	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") UUID id){
		Map<String, Object> mapResponse = new HashMap<String, Object>();
		try {
			Product product = productService.findById(id);
			
			this.responseAddObject(mapResponse, this.convertToDto(product));
			return this.responseReturnOK(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}

	}
	
	@PutMapping("/create")
	public ResponseEntity<?> updateProduct(@RequestBody ProductInDTO productDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			Product product = this.convertToEntity(productDTO);
			product = productService.save(product);
			
			this.responseAddObject(mapResponse, this.convertToDto(product));
			return this.responseReturnOKWithMessageDefault(mapResponse);			
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listProducts() {
		Map<String, Object> mapResponse = new HashMap<String, Object>();	
		try {
			List<ProductOutDTO> listUnits =  productService
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
	
	private ProductOutDTO convertToDto(Product product) {
		return modelMapper.map(product, ProductOutDTO.class);
	}
	
	private Product convertToEntity(ProductInDTO dto) {
		Product product = modelMapper.map(dto, Product.class);
		product.setUnit(unitService.findById(dto.getIdUnit()));
		
		return product;
	}
}
