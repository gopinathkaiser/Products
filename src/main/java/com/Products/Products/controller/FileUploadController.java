package com.Products.Products.controller;

import com.Products.Products.Entity.ProductImage;
import com.Products.Products.service.Impl.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return  fileUploadService.uploadFile(file);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        ProductImage fileEntity = fileUploadService.getFile(fileId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileEntity.getFileName())
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .body(fileEntity.getData());
    }
}
