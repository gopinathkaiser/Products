package com.Products.Products.service.Impl;

import com.Products.Products.DAO.Service.UserRepoService;
import com.Products.Products.DAO.UserRoleRepo;
import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.DTO.LoginDTO;
import com.Products.Products.DTO.SignupDTO;
import com.Products.Products.Entity.Role;
import com.Products.Products.Entity.UserCredentials;
import com.Products.Products.Util.JwtUtils;
import com.Products.Products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserSignupRepo userSignupRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepoService userRepoService;


    @Override
    public ResponseEntity<String> validateData(LoginDTO loginDTO) {
        return userRepoService.validateData(loginDTO);
    }

    @Override
    public ResponseEntity<String> inserData(SignupDTO signupDTO) {
        return userRepoService.insertData(signupDTO);
    }
}
