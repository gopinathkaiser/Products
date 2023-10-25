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
        Optional<UserCredentials> userCredentials = userSignupRepo.findByuid(sellerDTO.getUid());
        System.out.println("before if");
        System.out.println(userCredentials);
        if(!userCredentials.isEmpty()){
            if(!userCredentials.get().getUid().equals(sellerDTO.getUid())){
                return new  ResponseEntity<>("Register first", HttpStatusCode.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
            }

            if(userCredentials.get().getRole().getRoleName().equals("seller")){

                Seller seller = Seller.builder()
                        .sellerName(sellerDTO.getSellerName())
                        .sellerAddress(sellerDTO.getSellerAddress())
                        .uid(sellerDTO.getUid())
                        .build();
                sellerRepo.save(seller);
            }else{
                return new  ResponseEntity<>("Only Seller", HttpStatusCode.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
            }
        }else{
            return new ResponseEntity<>("User not registered",HttpStatusCode.valueOf(HttpServletResponse.SC_BAD_GATEWAY));
        }
        return new ResponseEntity<>("Success",HttpStatusCode.valueOf(HttpServletResponse.SC_ACCEPTED));
    }

    @Override
    public List<Seller> getSeller() {
        System.out.println(sellerRepo.findAll());
        return sellerRepo.findAll();
    }
}
