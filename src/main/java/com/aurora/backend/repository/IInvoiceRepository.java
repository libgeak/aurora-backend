package com.aurora.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.backend.model.Customer;
import com.aurora.backend.model.Invoice;

@Repository
public interface IInvoiceRepository  extends JpaRepository<Invoice, UUID>{
	
	public List<Invoice> findAllByCustomerId(UUID id);

}
