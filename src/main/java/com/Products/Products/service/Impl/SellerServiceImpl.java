package com.Products.Products.service.Impl;

import com.Products.Products.DAO.SellerRepo;
import com.Products.Products.DAO.Service.SellerRepoService;
import com.Products.Products.DAO.UserRoleRepo;
import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Entity.Seller;
import com.Products.Products.Entity.UserCredentials;
import com.Products.Products.service.SellerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<String> addSeller(SellerDTO sellerDTO) {


        return sellerRepoService.addSeller(sellerDTO);
    }

    public ResponseEntity<List<Seller>> getSeller() {

        return sellerRepoService.getSeller();

    }
}
