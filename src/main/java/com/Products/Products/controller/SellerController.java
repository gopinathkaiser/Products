package com.Products.Products.controller;

import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Seller;
import com.Products.Products.service.Impl.SellerServiceImpl;
import com.Products.Products.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Seller>> getSeller(){
        return sellerService.getSeller();
    }

}
