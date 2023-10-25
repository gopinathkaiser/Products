package com.Products.Products.controller;

import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Seller;
import com.Products.Products.service.Impl.SellerServiceImpl;
import com.Products.Products.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seller")
public class SellerController {


    @Autowired
    private SellerService sellerService;

    @PostMapping("addSeller")
    public ResponseEntity<String> addSeller(@RequestBody SellerDTO sellerDTO){
        return sellerService.addSeller(sellerDTO);
    }

    @GetMapping("getSeller")
    @Cacheable(value = "GetSeller")
    public List<Seller> getSeller(){

        System.out.println("db for seller");
        return sellerService.getSeller();
    }

    @GetMapping("getSellerProdcuts/{sellerId}")
    @Cacheable(value = "SellerProducts",key = "#sellerId")
    public List<ProductResponseDTO>  getSellerProducts(@PathVariable Long sellerId){
        System.out.println("seller products db");
        return sellerService.getSellerProducts(sellerId);
    }

}
