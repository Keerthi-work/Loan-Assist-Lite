package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ CREATE PRODUCT
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO) {

        ProductDTO created = productService.createProduct(productDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ✅ FETCH PRODUCT BY ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable Long productId) {

        ProductDTO product = productService.fetchByProductId(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // ✅ FETCH ALL PRODUCTS
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> products = productService.fetchAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // ✅ UPDATE PRODUCT
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductDTO productDTO) {

        ProductDTO updated = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ✅ DELETE PRODUCT
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId) {

        String message = productService.deleteProduct(productId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // ✅ UPDATE PRODUCT STATUS
    @PutMapping("/{productId}/status")
    public ResponseEntity<ProductDTO> updateProductStatus(
            @PathVariable Long productId,
            @RequestParam String status) {

        ProductDTO updated = productService.updateProductStatus(productId, status);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ✅ FETCH PRODUCTS BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductDTO>> getProductsByStatus(
            @PathVariable String status) {

        List<ProductDTO> products = productService.fetchByStatus(status);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}