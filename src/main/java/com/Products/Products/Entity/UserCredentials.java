package com.Products.Products.Entity;

import jakarta.persistence.*;
import com.Products.Products.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uid;

    private String email;

    private String name;

    private String password;

    @ManyToOne
    private Role role;

}
