package com.Products.Products.service;

import com.Products.Products.DAO.UserRoleRepo;
import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.DTO.LoginDTO;
import com.Products.Products.DTO.SignupDTO;
import com.Products.Products.Entity.Role;
import com.Products.Products.Entity.UserCredentials;
import com.Products.Products.Util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

    public ResponseEntity<String> insertData(SignupDTO signupDTO) {

        Role role = userRoleRepo.findByRoleName(signupDTO.getRole().getRoleName());
        if(role == null){
            return new ResponseEntity<>("Invalid Role",HttpStatus.BAD_REQUEST);
        }
        UserCredentials userCredentials = UserCredentials.builder()
                .email(signupDTO.getEmail())
                .name(signupDTO.getName())
                .password(passwordEncoder.encode(signupDTO.getPassword()))
                .role(role)
                .build();

        userSignupRepo.save(userCredentials);
        return new ResponseEntity<>("Successfully added", HttpStatus.ACCEPTED);

    }

    public ResponseEntity<String> validateData(LoginDTO loginDTO) {
//        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        UserCredentials user = userSignupRepo.getByEmail(loginDTO.getEmail());
        if(user.getPassword().equals(loginDTO.getPassword())){
            return new ResponseEntity<>(jwtUtils.generateToken(loginDTO.getEmail()),HttpStatus.OK);
        }else{
            throw new UsernameNotFoundException("not found");
        }
    }
}
