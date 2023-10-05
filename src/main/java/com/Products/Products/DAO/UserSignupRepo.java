package com.Products.Products.DAO;

import com.Products.Products.Entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSignupRepo extends JpaRepository<UserCredentials, Long> {
    Optional<UserCredentials> getByEmail(String email);

    Optional<UserCredentials> findByName(String username);

    Optional<UserCredentials> findByEmail(String username);
}
