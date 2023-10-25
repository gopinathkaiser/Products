package com.Products.Products.controller;

import com.Products.Products.DAO.ProductsRepo;
import com.Products.Products.DTO.StatusDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Enum.SellerEnum;
import com.Products.Products.service.ProductsService;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    ProductsService productsService;

    @Autowired
    ProductsRepo productsRepo;

    @PostMapping("getProducts")
    @Cacheable(value = "ProductCache",key = "#status")
    public List<Products> getProducts(@RequestBody SellerEnum status){
        System.out.println("db for get products");
        return productsService.getProductsByStatus(status);
    }

    @PostMapping("changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody List<StatusDTO> statusData){
        return productsService.changeStatus(statusData);
    }


}
