package com.aurora.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "units")
public class Unit implements Serializable, ICustomEntity {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id; 
	
	private String code;
	
	private String name;
	
	private String state;
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unit", cascade = CascadeType.ALL)
	private List<Product> products;
	
}
