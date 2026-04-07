package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.PricingVersionDTO;
import com.example.demo.entity.Product;

public interface PricingVersionService 
{
    public PricingVersionDTO createPricingVersion(PricingVersionDTO dto);
    public PricingVersionDTO fetchByPricingId(Long pricingId);
    public List<PricingVersionDTO> fetchAllPricingVersions();
    public List<PricingVersionDTO> fetchByProductId(Product product);
    public PricingVersionDTO updatePricingVersion(Long pricingId, PricingVersionDTO dto);
    public String deletePricingVersion(Long pricingId);
}