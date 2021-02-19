package com.aurora.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurora.backend.config.CustomComponentConfig;
import com.aurora.backend.enums.CustomExceptionEnum;
import com.aurora.backend.enums.MessageEncoderEnum;
import com.aurora.backend.model.Unit;
import com.aurora.backend.repository.IUnitRepository;
import com.aurora.backend.service.IUnitService;
@Service
public class UnitServiceImpl extends CustomComponentConfig implements IUnitService {

	@Autowired
	private IUnitRepository unitRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Unit> findAll() {
		return unitRepository.findAll();
	}

	@Override
	@Transactional
	public Unit save(Unit unit) {
		validateUniqueUnit(unit);
		return unitRepository.save(unit);
	}

	@Override
	@Transactional(readOnly = true)
	public Unit findById(Long id) {
		return unitRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Unit> findAllByStateActive() {
		return unitRepository.findAllByState("A");
	}
	
	private void validateUniqueUnit(Unit unit) {
		Unit unitOld = this.unitRepository.findByCode(unit.getCode());
		if(unitOld != null && unitOld.getId() != unit.getId()) {
			generateException.throwCustomException(
					CustomExceptionEnum.ERROR, 
					MessageEncoderEnum.UNIT_VALID_UNIQUE_CODE,
					unit.getCode(),
					unit.getName());
		}
		
	}

}
