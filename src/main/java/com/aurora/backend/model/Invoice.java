package com.aurora.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="invoices")
public class Invoice implements Serializable, ICustomEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name ="system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;	
	
	@Column(name ="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
		
	private double total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customers_id")
	private Customer customer;

}
