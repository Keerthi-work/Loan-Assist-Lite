package com.example.demo.service;
 
import java.util.List;

import com.example.demo.dto.ProductDTO;

public interface ProductService 
{
    public ProductDTO createProduct(ProductDTO productDTO);
    public ProductDTO fetchByProductId(Long productId);
    public List<ProductDTO> fetchAllProducts();
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    public String deleteProduct(Long productId);
    public ProductDTO updateProductStatus(Long productId, String status);
    public List<ProductDTO> fetchByStatus(String status);
}

 