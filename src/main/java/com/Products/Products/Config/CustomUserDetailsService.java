package com.Products.Products.Config;

import com.Products.Products.DAO.UserSignupRepo;
import com.Products.Products.Entity.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserSignupRepo userSignupRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> credentials = userSignupRepo.findByEmail(username);

        return credentials.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("name not found " + username));
    }
}
