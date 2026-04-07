package com.example.demo.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.Status;
 
public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByStatus(Status valueOf);
 
}

 