package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.domain.entity.ConfirmationToken;
import com.project.restaurant.domain.repository.ConfirmationTokenRepository;
import com.project.restaurant.domain.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    @CacheEvict(value = "token", allEntries = true)
    public ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    @Cacheable("token")
    public ConfirmationToken findTokenByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }
}
