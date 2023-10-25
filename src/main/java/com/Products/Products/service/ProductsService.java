package com.Products.Products.service;

import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.ProductsRequestDTO;
import com.Products.Products.DTO.StatusDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Enum.SellerEnum;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductsService {
    ResponseEntity<?> getProducts();

    ResponseEntity<?> changeStatus(List<StatusDTO> statusData);

    List<ProductResponseDTO> getAllProducts();

    List<Products> getProductsByStatus(SellerEnum status);

    ResponseEntity<?> getSpecificSeller(String email);

    ResponseEntity<?> addProducts(ProductsRequestDTO produtsDTO);
}
