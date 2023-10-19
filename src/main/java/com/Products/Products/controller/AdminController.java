package com.Products.Products.controller;

import com.Products.Products.DTO.StatusDTO;
import com.Products.Products.Enum.SellerEnum;
import com.Products.Products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    ProductsService productsService;

    @GetMapping("getProducts")
    public ResponseEntity<?> getProducts(){
        return productsService.getProductsForAdmin();
    }

    @GetMapping("getProductsByStatus/{status}")
    public ResponseEntity<?> getProductsByStatus(@PathVariable SellerEnum status){
        return productsService.getProductsByStatus(status);
    }

    @PostMapping("changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody List<StatusDTO> statusData){
        return productsService.changeStatus(statusData);
    }

}
