package com.project.restaurant.unit.service;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.adapters.serviceimpl.UserServiceImpl;
import com.project.restaurant.domain.repository.UserDescriptionRepository;
import com.project.restaurant.domain.repository.UserRepository;
import com.project.restaurant.domain.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.project.restaurant.util.Utility.USER_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final UserRepository userRepository = mock();
    private final UserDescriptionRepository userDescriptionRepository = mock();

    private final UserService userService = new UserServiceImpl(userRepository, userDescriptionRepository);

    @Test
    void testGetUserById() {
        when(userRepository.findById(USER_LIST.get(0).getId())).thenReturn(Optional.ofNullable(USER_LIST.get(0)));

        assertThat(userService.getUserById(USER_LIST.get(0).getId())).isEqualTo(USER_LIST.get(0));
    }

    @Test
    void testGetUserByIdWhenInvalidId() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(10)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testGetUserByUsername() {
        when(userRepository.findByUsername(USER_LIST.get(0).getUsername())).thenReturn(Optional.ofNullable(USER_LIST.get(0)));

        assertThat(userService.getUserByUsername(USER_LIST.get(0).getUsername())).isEqualTo(USER_LIST.get(0));
    }

    @Test
    void testGetUserByIdWhenInvalidUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserByUsername("test")).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(USER_LIST.get(0))).thenReturn(USER_LIST.get(0));
        when(userDescriptionRepository.save(USER_LIST.get(0).getUserDescription())).thenReturn(USER_LIST.get(0).getUserDescription());

        assertThat(userService.save(USER_LIST.get(0))).isEqualTo(USER_LIST.get(0));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(USER_LIST);

        assertThat(userService.getAllUsers()).isEqualTo(USER_LIST);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(USER_LIST.get(0).getId())).thenReturn(Optional.ofNullable(USER_LIST.get(0)));
        when(userRepository.save(USER_LIST.get(0))).thenReturn(USER_LIST.get(0));

        userService.updateUser(USER_LIST.get(0));

        verify(userRepository).save(USER_LIST.get(0));
    }

    @Test
    void testUpdateUserWhenUserDoesNotExist() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(USER_LIST.get(0))).isInstanceOf(NoSuchElementException.class);
    }
}
