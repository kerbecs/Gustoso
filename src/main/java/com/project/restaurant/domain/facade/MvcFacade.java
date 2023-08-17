package com.project.restaurant.domain.facade;

import com.project.restaurant.domain.dto.*;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface MvcFacade {
    List<ProduceDto> getAllProduces();

    OrderDto makeOrder(String username, UserOrderDto userOrderDto);

    UserDto getUserByUsername(String username);

    UserDto saveUser(UserRegisterDto userDto);

    UserProfileDto saveUser(UserProfileDto userProfileDto);

    UserProfileDto getUserProfileByUsername(String username);

    void checkPasswords(String password1, String password2, BindingResult bindingResult);

}
