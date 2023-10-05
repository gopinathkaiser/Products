package com.Products.Products.controller;

import com.Products.Products.DTO.LoginDTO;
import com.Products.Products.DTO.SignupDTO;
import com.Products.Products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    UserService productService;

    @PostMapping("/add")
    public ResponseEntity<String> insertData(@RequestBody SignupDTO signupDTO){
        return productService.insertData(signupDTO);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> validate(@RequestBody LoginDTO loginDTO){
        return productService.validateData(loginDTO);
    }

}
