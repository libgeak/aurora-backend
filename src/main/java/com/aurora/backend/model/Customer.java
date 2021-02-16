package com.aurora.backend.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Table(name ="customers")
@Entity
public class Customer implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name ="system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name="identification_number", length = 15)
	private String identificationNumber;
	
	private String fullname;
	
	private String email;
	
	@Column(length = 15)
	private String telephone;
	
	private String direction;
	
	private String city;
	
	private String department;
	
	private String observations;

}
