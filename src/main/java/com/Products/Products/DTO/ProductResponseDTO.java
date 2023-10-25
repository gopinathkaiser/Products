package com.Products.Products.DTO;

import com.Products.Products.Enum.SellerEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include. NON_NULL)
public class ProductResponseDTO implements Serializable {

    private Long pid;

    private String productName;

    private String productPrice;

    private String productCategory;

    private SellerEnum status;

    public ProductResponseDTO(Long pid, String productName, String productPrice, String productCategory) {
        this.pid = pid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }
}
