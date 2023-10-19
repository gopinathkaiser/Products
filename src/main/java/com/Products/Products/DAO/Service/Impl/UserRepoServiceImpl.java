package com.Products.Products.DAO.Service.Impl;

import com.Products.Products.DAO.Service.UserRepoService;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepoServiceImpl implements UserRepoService {

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

    @Override
    public ResponseEntity<String> insertData(SignupDTO signupDTO) {
        Optional<UserCredentials> dataFromDb = userSignupRepo.findByEmail(signupDTO.getEmail());

        if(!dataFromDb.isEmpty())
            return new ResponseEntity<>("Email already Exists", HttpStatus.FORBIDDEN);

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

    @Override
    public ResponseEntity<String> validateData(LoginDTO loginDTO) {
        UserCredentials user = userSignupRepo.findByEmail(loginDTO.getEmail()).get();
        if(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            return new ResponseEntity<>(jwtUtils.generateToken(loginDTO.getEmail(),user.getRole().getRoleName()),HttpStatus.OK);
        }else{
            throw new UsernameNotFoundException("not found");
        }
    }
}
