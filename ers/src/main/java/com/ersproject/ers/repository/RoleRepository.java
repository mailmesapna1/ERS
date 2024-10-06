package com.ersproject.ers.repository;

import com.ersproject.ers.model.Role;
import com.ersproject.ers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByAuthority(String role);
}
