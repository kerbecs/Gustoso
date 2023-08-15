package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.domain.entity.ConfirmationToken;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.repository.ConfirmationTokenRepository;
import com.project.restaurant.domain.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken findTokenByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }
}
