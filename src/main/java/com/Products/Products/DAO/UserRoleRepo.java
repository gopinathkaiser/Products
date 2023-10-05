package com.Products.Products.DAO;

import com.Products.Products.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
