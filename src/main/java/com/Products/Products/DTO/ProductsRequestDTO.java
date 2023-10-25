package com.Products.Products.DTO;

import com.Products.Products.Entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsRequestDTO {

    private Seller seller;

    private List<ProductsDTO> productsList;
}
