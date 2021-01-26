package com.aurora.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.backend.model.Product;
@Repository
public interface IProductRepository  extends JpaRepository<Product, UUID>{

}
