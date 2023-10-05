package com.Products.Products.DTO;

import com.Products.Products.Entity.Role;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class SignupDTO {

    private String email;

    private String name;

    private String password;

    private Role role;
}
