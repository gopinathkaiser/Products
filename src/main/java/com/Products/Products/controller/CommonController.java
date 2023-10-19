package com.Products.Products.controller;

import com.Products.Products.DAO.ProductsRepo;
import com.Products.Products.Entity.Products;
import com.Products.Products.service.ProductsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Common")
public class CommonController {

    @Autowired
    ProductsRepo productsRepo;

    @Autowired
    ProductsService productsService;

    @GetMapping("getProducts")
    public ResponseEntity<?> getProducts(){
        return productsService.getAllProducts();
    }

}
