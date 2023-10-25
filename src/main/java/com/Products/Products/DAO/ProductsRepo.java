package com.Products.Products.DAO;

import com.Products.Products.Entity.Products;
import com.Products.Products.Enum.SellerEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Long> {
    List<Products> findAllByStatus(SellerEnum sellerEnum);

    List<Products> findByseller_sid(Long sellerId);
}
