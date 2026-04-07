package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Product.Status;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    //  ENTITY → DTO
    private ProductDTO convertToDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(entity.getProductId());
        dto.setName(entity.getName());
        dto.setMaxAmount(entity.getMaxAmount());
        dto.setMinAmount(entity.getMinAmount());
        dto.setMaxTermMonths(entity.getMaxTermMonths());
        dto.setInterestRateAnnual(entity.getInterestRateAnnual());
        dto.setFeeStructureJSON(entity.getFeeStructureJSON());
        dto.setEligibilityNotes(entity.getEligibilityNotes());
        dto.setStatus(entity.getStatus().name());
        return dto;
    }

    //  DTO → ENTITY
    private Product convertToEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setProductId(dto.getProductId());
        entity.setName(dto.getName());
        entity.setMaxAmount(dto.getMaxAmount());
        entity.setMinAmount(dto.getMinAmount());
        entity.setMaxTermMonths(dto.getMaxTermMonths());
        entity.setInterestRateAnnual(dto.getInterestRateAnnual());
        entity.setFeeStructureJSON(dto.getFeeStructureJSON());
        entity.setEligibilityNotes(dto.getEligibilityNotes());
        entity.setStatus(Status.valueOf(dto.getStatus()));
        return entity;
    }

    //  CREATE PRODUCT
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product entity = convertToEntity(productDTO);
        Product saved = repository.save(entity);
        return convertToDTO(saved);
    }

    // FETCH PRODUCT BY ID
    @Override
    public ProductDTO fetchByProductId(Long productId) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        return convertToDTO(product);
    }

    //  FETCH ALL PRODUCTS
    @Override
    public List<ProductDTO> fetchAllProducts() {
        List<Product> products = repository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();

        for (Product product : products) {
            dtoList.add(convertToDTO(product));
        }
        return dtoList;
    }

    //  UPDATE PRODUCT
    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {

        Product existing = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        existing.setName(productDTO.getName());
        existing.setMaxAmount(productDTO.getMaxAmount());
        existing.setMinAmount(productDTO.getMinAmount());
        existing.setMaxTermMonths(productDTO.getMaxTermMonths());
        existing.setInterestRateAnnual(productDTO.getInterestRateAnnual());
        existing.setFeeStructureJSON(productDTO.getFeeStructureJSON());
        existing.setEligibilityNotes(productDTO.getEligibilityNotes());
        existing.setStatus(Status.valueOf(productDTO.getStatus()));

        Product updated = repository.save(existing);
        return convertToDTO(updated);
    }

    // ✅ DELETE PRODUCT
    @Override
    public String deleteProduct(Long productId)
    {
        if (!repository.existsById(productId))
        {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
        repository.deleteById(productId);
        return "Product deleted successfully with ID: " + productId;
    }

    //  UPDATE PRODUCT STATUS
    @Override
    public ProductDTO updateProductStatus(Long productId, String status) 
    {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.setStatus(Status.valueOf(status));
        Product updated = repository.save(product);
        return convertToDTO(updated);
    }

    //  FETCH PRODUCTS BY STATUS
    @Override
    public List<ProductDTO> fetchByStatus(String status)
    {
        List<Product> products = repository.findByStatus(Status.valueOf(status));
        List<ProductDTO> dtoList = new ArrayList<>();

        for (Product product : products) {
            dtoList.add(convertToDTO(product));
        }
        return dtoList;
    }
}