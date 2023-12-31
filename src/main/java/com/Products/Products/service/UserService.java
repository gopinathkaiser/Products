package com.Products.Products.service;

import com.Products.Products.DTO.LoginDTO;
import com.Products.Products.DTO.SignupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<String> inserData(SignupDTO signupDTO);

    ResponseEntity<String> validateData(LoginDTO loginDTO);
}
