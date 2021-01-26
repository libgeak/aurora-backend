package com.aurora.backend.service;

import java.util.List;

import com.aurora.backend.model.Unit;

public interface IUnitService {

	public List<Unit> findAll();
	
	public Unit save(Unit unit);
	
	public Unit findById(Long id);
	
	public List<Unit> findAllByStateActive();

}
