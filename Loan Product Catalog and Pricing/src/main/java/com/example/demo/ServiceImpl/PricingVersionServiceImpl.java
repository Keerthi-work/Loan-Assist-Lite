package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PricingVersionDTO;
import com.example.demo.entity.PricingVersion;
import com.example.demo.entity.Product;
import com.example.demo.repository.PricingVersionRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.PricingVersionService;

@Service
public class PricingVersionServiceImpl implements PricingVersionService {

    @Autowired
    private PricingVersionRepository pricingVersionRepository;

    @Autowired
    private ProductRepository productRepository;

    // ✅ ENTITY → DTO
    private PricingVersionDTO convertToDTO(PricingVersion entity) {
        PricingVersionDTO dto = new PricingVersionDTO();
        dto.setPricingId(entity.getPricingId());
        dto.setProductId(entity.getProduct().getProductId());
        dto.setEffectiveFrom(entity.getEffectiveFrom());
        dto.setEffectiveTo(entity.getEffectiveTo());
        dto.setInterestRateAnnual(entity.getInterestRateAnnual());
        dto.setFeesJSON(entity.getFeesJSON());
        return dto;
    }

    // ✅ DTO → ENTITY
    private PricingVersion convertToEntity(PricingVersionDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found with ID: " + dto.getProductId()));

        PricingVersion entity = new PricingVersion();
        entity.setPricingId(dto.getPricingId());
        entity.setProduct(product);
        entity.setEffectiveFrom(dto.getEffectiveFrom());
        entity.setEffectiveTo(dto.getEffectiveTo());
        entity.setInterestRateAnnual(dto.getInterestRateAnnual());
        entity.setFeesJSON(dto.getFeesJSON());
        return entity;
    }

    // ✅ CREATE PRICING VERSION
    @Override
    public PricingVersionDTO createPricingVersion(PricingVersionDTO dto) {
        PricingVersion saved = pricingVersionRepository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    // ✅ FETCH BY PRICING ID
    @Override
    public PricingVersionDTO fetchByPricingId(Long pricingId) {
        PricingVersion pricing = pricingVersionRepository.findById(pricingId)
                .orElseThrow(() ->
                        new RuntimeException("PricingVersion not found with ID: " + pricingId));
        return convertToDTO(pricing);
    }

    // ✅ FETCH ALL PRICING VERSIONS
    @Override
    public List<PricingVersionDTO> fetchAllPricingVersions() {
        List<PricingVersion> pricingList = pricingVersionRepository.findAll();
        List<PricingVersionDTO> dtoList = new ArrayList<>();

        for (PricingVersion pv : pricingList) {
            dtoList.add(convertToDTO(pv));
        }
        return dtoList;
    }

    // ✅ FETCH BY PRODUCT ENTITY
    @Override
    public List<PricingVersionDTO> fetchByProductId(Product product) {

        List<PricingVersion> pricingList =
                pricingVersionRepository.findByProduct(product);

        List<PricingVersionDTO> dtoList = new ArrayList<>();
        for (PricingVersion pv : pricingList) {
            dtoList.add(convertToDTO(pv));
        }
        return dtoList;
    }

    // ✅ UPDATE PRICING VERSION
    @Override
    public PricingVersionDTO updatePricingVersion(Long pricingId, PricingVersionDTO dto) {

        PricingVersion existing = pricingVersionRepository.findById(pricingId)
                .orElseThrow(() ->
                        new RuntimeException("PricingVersion not found with ID: " + pricingId));

        existing.setEffectiveFrom(dto.getEffectiveFrom());
        existing.setEffectiveTo(dto.getEffectiveTo());
        existing.setInterestRateAnnual(dto.getInterestRateAnnual());
        existing.setFeesJSON(dto.getFeesJSON());

        PricingVersion updated = pricingVersionRepository.save(existing);
        return convertToDTO(updated);
    }

    // ✅ DELETE PRICING VERSION
    @Override
    public String deletePricingVersion(Long pricingId) {

        if (!pricingVersionRepository.existsById(pricingId)) {
            throw new RuntimeException("PricingVersion not found with ID: " + pricingId);
        }

        pricingVersionRepository.deleteById(pricingId);
        return "PricingVersion deleted successfully with ID: " + pricingId;
    }
}
