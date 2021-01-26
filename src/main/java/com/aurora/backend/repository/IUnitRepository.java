package com.aurora.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.backend.model.Unit;

@Repository
public interface IUnitRepository extends JpaRepository<Unit, Long> {
	
	public List<Unit> findAllByState(String state);
	
	public Unit findByCode(String code);

}
