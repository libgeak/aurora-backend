package com.aurora.backend.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.aurora.backend.model.Customer;
import com.aurora.backend.model.Invoice;

@Repository
public interface IInvoiceRepository  extends JpaRepository<Invoice, UUID>{
	
	public List<Invoice> findAllByCustomerId(UUID id);
	
	@Query(value = "SELECT SUM(i.total) FROM Invoice i WHERE i.createdAt >= :createAt")
	public Double sumTotalInvoicesByDate(@Param("createAt") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date);

}
