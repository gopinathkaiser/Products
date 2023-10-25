package com.Products.Products.DTO;

import com.Products.Products.Entity.Products;
import lombok.Data;

import java.util.List;

@Data
public class SellerDTO {


    private Long uid;

    private String sellerName;

    private String sellerAddress;
}
