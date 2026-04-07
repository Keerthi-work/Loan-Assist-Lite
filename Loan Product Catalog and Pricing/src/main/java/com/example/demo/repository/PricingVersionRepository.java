package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PricingVersion;
import com.example.demo.entity.Product;

public interface PricingVersionRepository extends JpaRepository<PricingVersion, Long> {

	public List<PricingVersion> findByProduct(Product product);

	public Optional<Product> findActivePricing(Product product, LocalDate asOfDate);

}
