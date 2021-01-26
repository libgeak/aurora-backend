package com.aurora.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.backend.dto.UnitOutDTO;
import com.aurora.backend.model.Unit;
import com.aurora.backend.service.IUnitService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("units")
public class UnitRestController extends CustomAbstractRestController {
	
	private Logger logger = LoggerFactory.getLogger(UnitRestController.class);

	@Autowired
	private IUnitService unitService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createUnit(@RequestBody UnitOutDTO unitOutDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();					
		try {			
			Unit unit = this.convertToEntity(unitOutDTO);
			unit.setCode(unit.getCode().toUpperCase());
			unit = unitService.save(unit);
			
			this.responseAddObject(mapResponse, this.convertToDto(unit));
			return this.responseReturnOKWithMessageDefault(mapResponse);		

		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}		
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> createUnit(@PathVariable("id") Long id) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();			
		try {
			Unit unit = unitService.findById(id);	
			
			this.responseAddObject(mapResponse, this.convertToDto(unit));			
			return this.responseReturnOK(mapResponse);		
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
	
		}
		
	}
	
	@PutMapping("/create")
	public ResponseEntity<?> updateUnit(@RequestBody UnitOutDTO unitOutDTO) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();			
		Unit unit = this.convertToEntity(unitOutDTO);
		
		try {
			unit.setCode(unit.getCode().toUpperCase());
			unit = unitService.save(unit);
			
			this.responseAddObject(mapResponse, this.convertToDto(unit));
			return this.responseReturnOKWithMessageDefault(mapResponse);		
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
		}			
	}
	

	@GetMapping({"/list", "/list/{state}"})
	public ResponseEntity<?> listUnits(@PathVariable(required = false) String state){
		Map<String, Object> mapResponse = new HashMap<String, Object>();			
		List<UnitOutDTO> listUnits; 
		try {
			listUnits = listUnitFactory(state) 				
					.stream()
					.map(this::convertToDto)
					.collect(Collectors.toList()); 	
			
			this.responseAddObject(mapResponse, listUnits);			
			return this.responseReturnOK(mapResponse);		
		}catch(Exception ex) {
			return this.managerException(mapResponse, ex, logger);
	
		}				
	}
	
	private List<Unit> listUnitFactory(String state) {
		if(StringUtils.equals(state, "A")) {
			return unitService.findAllByStateActive();
		}
		return unitService.findAll();		
	}
	

	private UnitOutDTO convertToDto(Unit entity) {
		return modelMapper.map(entity, UnitOutDTO.class);
	}
	private Unit convertToEntity(UnitOutDTO dto) {
		return modelMapper.map(dto, Unit.class);
	}
	
}
