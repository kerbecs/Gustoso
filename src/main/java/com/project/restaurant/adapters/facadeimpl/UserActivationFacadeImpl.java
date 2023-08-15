package com.project.restaurant.adapters.facadeimpl;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserProfileDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.entity.ConfirmationToken;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.facade.UserActivationFacade;
import com.project.restaurant.domain.service.ConfirmationTokenService;
import com.project.restaurant.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivationFacadeImpl implements UserActivationFacade {
    private final UserService userService;
    private final ConfirmationTokenService tokenService;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public boolean activateUserByToken(String token) {
        ConfirmationToken confirmationToken = tokenService.findTokenByConfirmationToken(token);

        if (confirmationToken == null)
            return false;
        User user = confirmationToken.getUser();

        if (confirmationToken.getExpireDate().isBefore(LocalDateTime.now()) || user.getIsActive() == 1)
            return false;

        userService.activateUser(user.getUsername());

        return true;
    }
}

