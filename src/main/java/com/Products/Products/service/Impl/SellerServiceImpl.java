package com.Products.Products.service.Impl;

import com.Products.Products.DAO.ProductsRepo;
import com.Products.Products.DAO.SellerRepo;
import com.Products.Products.DAO.Service.SellerRepoService;
import com.Products.Products.DAO.UserRoleRepo;
import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Entity.Seller;
import com.Products.Products.Entity.UserCredentials;
import com.Products.Products.service.SellerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {


    @Autowired
    SellerRepoService sellerRepoService;

    @Autowired
    ProductsRepo productsRepo;

    public ResponseEntity<String> addSeller(SellerDTO sellerDTO) {


        return sellerRepoService.addSeller(sellerDTO);
    }

    public List<Seller> getSeller() {

        return sellerRepoService.getSeller();

    }

    @Override
    public List<ProductResponseDTO> getSellerProducts(Long sellerId) {
        try{
            List<Products> productsList = productsRepo.findByseller_sid(sellerId);
            List<ProductResponseDTO> productResponseList = new ArrayList<>();
            System.out.println(productsList);
            for(Products products : productsList){
                ProductResponseDTO productResponse = ProductResponseDTO.builder()
                        .pid(products.getPid())
                        .productName(products.getProductName())
                        .productCategory(products.getProductCategory())
                        .productPrice(products.getProductPrice())
                        .status(products.getStatus())
                        .build();
                productResponseList.add(productResponse);
            }
            return productResponseList ;
        }catch (Exception e){
            return null;
        }
    }
}
