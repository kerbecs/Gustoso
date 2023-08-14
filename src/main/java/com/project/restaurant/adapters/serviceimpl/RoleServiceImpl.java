package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.domain.entity.Role;
import com.project.restaurant.domain.repository.RoleRepository;
import com.project.restaurant.domain.roles.Roles;
import com.project.restaurant.domain.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRole(Roles role) {
        return roleRepository.findByRole(role).orElseThrow(() ->
            new NoSuchElementException("Role "+role.toString() + " does not exists")
        );
    }
}
