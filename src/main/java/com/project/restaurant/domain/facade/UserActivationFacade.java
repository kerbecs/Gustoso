package com.project.restaurant.domain.facade;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserProfileDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserActivationFacade {
    boolean activateUserByToken(String token);

}
