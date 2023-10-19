package com.Products.Products.controller;

import com.Products.Products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class productsController {

    @Autowired
    ProductsService productsService;

    @GetMapping("get")
    public ResponseEntity<?> getProducts(){
        return productsService.getProducts();

    }

}