package com.Products.Products.DAO.Service.Impl;

import com.Products.Products.DAO.SellerRepo;
import com.Products.Products.DAO.Service.SellerRepoService;
import com.Products.Products.DAO.UserRoleRepo;
import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.DTO.SellerDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Entity.Seller;
import com.Products.Products.Entity.UserCredentials;
import com.Products.Products.Enum.SellerEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerRepoServiceImpl implements SellerRepoService {


    @Autowired
    SellerRepo sellerRepo;

    @Autowired
    UserSignupRepo userSignupRepo;

    @Autowired
    UserRoleRepo userRoleRepo;
    @Override
    public ResponseEntity<String> addSeller(SellerDTO sellerDTO) {
        Optional<UserCredentials> userCredentials = userSignupRepo.getByEmail(sellerDTO.getEmail());
        List<Products> productsDetails = new ArrayList<>();
        System.out.println("before if");

        if(!userCredentials.isEmpty()){
            if(!userCredentials.get().getEmail().equals(sellerDTO.getEmail())){
                return new  ResponseEntity<>("Register first", HttpStatusCode.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
            }

            if(userCredentials.get().getRole().getRoleName().equals("seller")){
                List<Products> productsList = sellerDTO.getProducts();
                for(Products products : productsList){
                    Products product = Products.builder()
                            .productName(products.getProductName())
                            .productCategory(products.getProductCategory())
                            .productPrice(products.getProductPrice())
                            .status(SellerEnum.PENDING)
                            .build();

                    productsDetails.add(product);

                }

                Seller seller = Seller.builder()
                        .sellerName(sellerDTO.getSellerName())
                        .sellerAddress(sellerDTO.getSellerAddress())
                        .role(userCredentials.get().getRole())
                        .products(productsDetails)
                        .build();
                System.out.println(seller);
                sellerRepo.save(seller);
            }else{
                return new  ResponseEntity<>("Only Seller", HttpStatusCode.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
            }
        }else{
            return new ResponseEntity<>("Email not registered",HttpStatusCode.valueOf(HttpServletResponse.SC_BAD_GATEWAY));
        }
        return new ResponseEntity<>("Success",HttpStatusCode.valueOf(HttpServletResponse.SC_ACCEPTED));
    }

    @Override
    public ResponseEntity<List<Seller>> getSeller() {
        return new ResponseEntity<>(sellerRepo.findAll(), HttpStatusCode.valueOf(HttpServletResponse.SC_ACCEPTED));
    }
}
