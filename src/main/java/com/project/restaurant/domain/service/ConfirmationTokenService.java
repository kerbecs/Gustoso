package com.project.restaurant.domain.service;

import com.project.restaurant.domain.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken);

    ConfirmationToken findTokenByConfirmationToken(String confirmationToken);
}
