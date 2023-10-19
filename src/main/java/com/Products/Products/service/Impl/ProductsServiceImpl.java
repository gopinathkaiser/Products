package com.Products.Products.service.Impl;

import com.Products.Products.DAO.ProductsRepo;
import com.Products.Products.DTO.ProductResponseDTO;
import com.Products.Products.DTO.StatusDTO;
import com.Products.Products.Entity.Products;
import com.Products.Products.Enum.SellerEnum;
import com.Products.Products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.Products.Products.Enum.SellerEnum.PENDING;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    ProductsRepo productsRepo;

    @Override
    public ResponseEntity<?> getProducts() {
        try{
            List<Products> products = productsRepo.findAllByStatus(PENDING);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProductsForAdmin() {
        try{
            List<Products> products = productsRepo.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProductsByStatus(SellerEnum status) {
        try{
            List<Products> products = productsRepo.findAllByStatus(status);
            if(products.isEmpty())
                return new ResponseEntity<>("No Data Found", HttpStatus.OK);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
                        .build();

                productsRepo.save(productsData);
            }
            return new ResponseEntity<>("Modified successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        try{
            List<Products> products = productsRepo.findAllByStatus(SellerEnum.ACCEPTED);
            List<ProductResponseDTO> productResponse = products.stream()
                    .map(productsData -> new ProductResponseDTO(
                            productsData.getPid(),
                            productsData.getProductName(),
                            productsData.getProductPrice(),
                            productsData.getProductCategory()
                    ))
                    .collect(Collectors.toList());


            return new ResponseEntity<>(productResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occured" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
