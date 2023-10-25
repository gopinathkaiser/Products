package com.Products.Products.DAO;

import com.Products.Products.Entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepo extends JpaRepository<ProductImage,Long> {
}
