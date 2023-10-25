package com.Products.Products.controller;

import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.ProductsRequestDTO;
import com.Products.Products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class productsController {

    @Autowired
    ProductsService productsService;

    @GetMapping("get")
    @Cacheable(value = "CommonProduct")
    public List<ProductResponseDTO> getProducts(){
        return productsService.getAllProducts();
    }

    @GetMapping("getSpecificSellerData/{email}")
//    @Cacheable(value = "SpecificSeller" ,key = "#email")
    public ResponseEntity<?> getSpecificSeller(@PathVariable String email){
        System.out.println("seller db called");
        return productsService.getSpecificSeller(email);
    }

    @PostMapping("addProducts")
    public ResponseEntity<?> addProducts(@RequestBody ProductsRequestDTO productsRequestDTO){
        return productsService.addProducts(productsRequestDTO);
    }

}
