package com.Products.Products.controller;

import com.Products.Products.DTO.LoginDTO;
import com.Products.Products.DTO.SignupDTO;
import com.Products.Products.service.Impl.UserServiceImpl;
import com.Products.Products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    UserServiceImpl productService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> insertData(@RequestBody SignupDTO signupDTO){
        return userService.inserData(signupDTO);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> validate(@RequestBody LoginDTO loginDTO){

        return userService.validateData(loginDTO);
    }

}
