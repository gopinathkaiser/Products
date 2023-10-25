package com.Products.Products.service;


import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {
    ResponseEntity<String> addSeller(SellerDTO sellerDTO);

    List<Seller> getSeller();

    List<ProductResponseDTO>  getSellerProducts(Long sellerId);
}
