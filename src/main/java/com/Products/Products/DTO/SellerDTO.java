package com.Products.Products.DTO;

import com.Products.Products.Entity.Products;
import lombok.Data;

import java.util.List;

@Data
public class SellerDTO {

    private String email;

    private String sellerName;

    private String sellerAddress;

    private List<Products> products;
}
