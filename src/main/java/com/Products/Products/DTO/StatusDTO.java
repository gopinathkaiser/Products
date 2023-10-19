package com.Products.Products.DTO;

import com.Products.Products.Enum.SellerEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long productId;

    private SellerEnum productStatus;
}
