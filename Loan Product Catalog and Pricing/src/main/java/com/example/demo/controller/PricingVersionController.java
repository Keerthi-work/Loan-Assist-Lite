package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PricingVersionDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.PricingVersionService;

@RestController
@RequestMapping("/pricing-versions")
public class PricingVersionController {

    @Autowired
    private PricingVersionService pricingVersionService;

    // ✅ CREATE PRICING VERSION
    @PostMapping("/add")
    public ResponseEntity<PricingVersionDTO> createPricingVersion(
            @RequestBody PricingVersionDTO dto) {

        PricingVersionDTO created =
                pricingVersionService.createPricingVersion(dto);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ✅ FETCH PRICING VERSION BY ID
    @GetMapping("/{pricingId}")
    public ResponseEntity<PricingVersionDTO> getPricingVersionById(
            @PathVariable Long pricingId) {

        PricingVersionDTO dto =
                pricingVersionService.fetchByPricingId(pricingId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // ✅ FETCH ALL PRICING VERSIONS
    @GetMapping("/all")
    public ResponseEntity<List<PricingVersionDTO>> getAllPricingVersions() {

        List<PricingVersionDTO> list =
                pricingVersionService.fetchAllPricingVersions();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // ✅ FETCH PRICING VERSIONS BY PRODUCT
    // Product object expected by service
    @PostMapping("/by-product")
    public ResponseEntity<List<PricingVersionDTO>> getByProduct(
            @RequestBody Product product) {

        List<PricingVersionDTO> list =
                pricingVersionService.fetchByProductId(product);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // ✅ UPDATE PRICING VERSION
    @PutMapping("/update/{pricingId}")
    public ResponseEntity<PricingVersionDTO> updatePricingVersion(
            @PathVariable Long pricingId,
            @RequestBody PricingVersionDTO dto) {

        PricingVersionDTO updated =
                pricingVersionService.updatePricingVersion(pricingId, dto);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ✅ DELETE PRICING VERSION
    @DeleteMapping("/delete/{pricingId}")
    public ResponseEntity<String> deletePricingVersion(
            @PathVariable Long pricingId) {

        String message =
                pricingVersionService.deletePricingVersion(pricingId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}