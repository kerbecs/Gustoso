package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.Role;
import com.project.restaurant.domain.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(Roles role);
}
