package com.project.restaurant.domain.service;

import com.project.restaurant.domain.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String username);
    User save(User user);
    List<User> getAllUsers();
    void updateUser(User user);

    void activateUser(String username);
}
