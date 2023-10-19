package com.Products.Products.DAO.Service;

import com.Products.Products.DTO.LoginDTO;
import com.Products.Products.DTO.SignupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserRepoService {
    ResponseEntity<String> insertData(SignupDTO signupDTO);

    ResponseEntity<String> validateData(LoginDTO loginDTO);
}
