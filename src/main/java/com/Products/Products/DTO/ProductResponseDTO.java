package com.Products.Products.DTO;

import com.Products.Products.Enum.SellerEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long pid;

    private String productName;

    private String productPrice;

    private String productCategory;

    @JsonIgnore
    private SellerEnum status;

    public ProductResponseDTO(Long pid, String productName, String productPrice, String productCategory) {
        this.pid = pid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }
}
