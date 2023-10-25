package com.Products.Products.Entity;

import com.Products.Products.Enum.SellerEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("Product")
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pid;

    private String productName;

    private String productPrice;

    private String productCategory;

    private SellerEnum status;

    @ManyToOne
    private Seller seller;
}

