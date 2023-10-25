package com.Products.Products.service.Impl;

import com.Products.Products.DAO.ProductsRepo;
import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.ProductsDTO;
import com.Products.Products.DTO.ProductsRequestDTO;
import com.Products.Products.DTO.StatusDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Entity.Seller;
import com.Products.Products.Entity.UserCredentials;
import com.Products.Products.Enum.SellerEnum;
import com.Products.Products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepo productsRepo;

    @Autowired
    UserSignupRepo userSignupRepo;

    @Autowired
    RedisTemplate redisTemplate;

    public static final String HASH_KEY = "Product";


    @Override
    public ResponseEntity<?> getProducts() {
        return null;
    }

    @Override
    public ResponseEntity<?> changeStatus(List<StatusDTO> statusData) {
        try{
            System.out.println(statusData);
            for (StatusDTO status : statusData){
                Optional<Products> products = productsRepo.findById(status.getProductId());
                if(!products.isPresent())
                    return new ResponseEntity<>("product not found\n product id : " + status.getProductId(), HttpStatus.OK);


                Products productsData = products.get();

                productsData = Products.builder()
                        .pid(products.get().getPid())
                        .productCategory(productsData.getProductCategory())
                        .productName(productsData.getProductName())
                        .productPrice(productsData.getProductPrice())
                        .status(status.getProductStatus())
                        .seller(productsData.getSeller())
                        .build();

                productsRepo.save(productsData);
            }
            return new ResponseEntity<>("Modified successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        try{
            List<Products> products = productsRepo.findAllByStatus(SellerEnum.ACCEPTED);
            System.out.println("databse 1");
            List<ProductResponseDTO> productResponse = products.stream()
                    .map(productsData -> new ProductResponseDTO(
                            productsData.getPid(),
                            productsData.getProductName(),
                            productsData.getProductPrice(),
                            productsData.getProductCategory()
                    ))
                    .collect(Collectors.toList());

            return productResponse;
//            return new ResponseEntity<>(productResponse,HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Products> getProductsByStatus(SellerEnum status) {
        try {
            List<Products> products;
            if (!status.equals(SellerEnum.EMPTY)) {
                System.out.println("db called");
                products = productsRepo.findAllByStatus(status);
            } else {
                products = productsRepo.findAll();
            }
            System.out.println("here");
            return products;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public ResponseEntity<?> getSpecificSeller(String email) {
        try{
            Optional<UserCredentials> userData = userSignupRepo.findByEmail(email);

            return new ResponseEntity<>("products", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> addProducts(ProductsRequestDTO productsRequestDTO) {
        try{
            List<ProductsDTO> products = productsRequestDTO.getProductsList();
            Seller seller = Seller.builder()
                    .sid(productsRequestDTO.getSeller().getSid())
                    .build();

            for(ProductsDTO productsDTO : products){
                Products productsData = Products.builder()
                        .seller(seller)
                        .productCategory(productsDTO.getProductCategory())
                        .productName(productsDTO.getProductName())
                        .productPrice(productsDTO.getProductPrice())
                        .status(SellerEnum.PENDING)
                        .build();
                redisTemplate.opsForHash().put(HASH_KEY,productsData.getPid(),productsData);
                productsRepo.save(productsData);
            }

            return new ResponseEntity<>("Products Added successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
