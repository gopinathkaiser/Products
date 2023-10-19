package com.Products.Products.service;

import com.Products.Products.DTO.StatusDTO;
import com.Products.Products.Enum.SellerEnum;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductsService {
    ResponseEntity<?> getProducts();

    ResponseEntity<?> getProductsForAdmin();

    ResponseEntity<?> getProductsByStatus(SellerEnum status);

    ResponseEntity<?> changeStatus(List<StatusDTO> statusData);

    ResponseEntity<?> getAllProducts();
}
