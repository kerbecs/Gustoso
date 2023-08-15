package com.project.restaurant.domain.facade;

import com.project.restaurant.domain.dto.UserDto;

import java.util.List;

public interface UserFacade {
    List<UserDto> getAllUsers();
    UserDto getUserByUsername(String username);

}
