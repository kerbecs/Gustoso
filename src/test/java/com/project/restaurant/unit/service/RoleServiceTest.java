package com.project.restaurant.unit.service;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.adapters.serviceimpl.RoleServiceImpl;
import com.project.restaurant.domain.repository.RoleRepository;
import com.project.restaurant.domain.roles.Roles;
import com.project.restaurant.domain.service.RoleService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.project.restaurant.util.Utility.ROLE_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleServiceTest {
    private final RoleRepository roleRepository = mock();
    private final RoleService roleService = new RoleServiceImpl(roleRepository);

    @Test
    void testGetRole(){
        when(roleRepository.findByRole(Roles.ADMIN)).thenReturn(Optional.ofNullable(ROLE_LIST.get(1)));

        assertThat(roleService.getRole(Roles.ADMIN)).isEqualTo(ROLE_LIST.get(1));
    }
    @Test
    void testGetRoleWhenRoleIsInvalid(){
        when(roleRepository.findByRole(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roleService.getRole(Roles.MEMBER)).isInstanceOf(NoSuchElementException.class);
    }
}

