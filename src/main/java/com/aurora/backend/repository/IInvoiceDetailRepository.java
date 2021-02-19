package com.aurora.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.backend.model.InvoiceDetail;

@Repository
public interface IInvoiceDetailRepository extends JpaRepository<InvoiceDetail, UUID>{
	
	public List<InvoiceDetail> findAllByInvoiceId(UUID id);

}
