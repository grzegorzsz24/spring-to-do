package com.example.projekt.repository;

import com.example.projekt.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByRole(String role);
}
