package com.project.restaurant.adapters.facadeimpl;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.facade.UserFacade;
import com.project.restaurant.domain.mapstruct.UserMapper;
import com.project.restaurant.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;
    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.userToUserDto(userService.getUserByUsername(username));
    }
}
