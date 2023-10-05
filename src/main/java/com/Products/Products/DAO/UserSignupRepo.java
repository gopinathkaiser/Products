package com.Products.Products.DAO;

import com.Products.Products.Entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSignupRepo extends JpaRepository<UserCredentials, Long> {
    UserCredentials getByEmail(String email);
}
