package com.Products.Products.service;


import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {
    ResponseEntity<String> addSeller(SellerDTO sellerDTO);

    ResponseEntity<List<Seller>> getSeller();
}
