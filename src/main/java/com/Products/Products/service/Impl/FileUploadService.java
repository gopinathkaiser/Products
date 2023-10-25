package com.Products.Products.service.Impl;

import com.Products.Products.DAO.FileUploadRepo;
import com.Products.Products.Entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileUploadService {

    @Autowired
    private FileUploadRepo fileUploadRepo;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();

        byte[] data = file.getBytes();

        ProductImage productImage = ProductImage.builder()
                .fileName(fileName)
                .fileType(fileType)
                .data(data)
                .build();
        fileUploadRepo.save(productImage);

        return "saved successfully";

    }

    public ProductImage getFile(Long fileId) {
        return fileUploadRepo.findById(fileId).get();
    }
}
