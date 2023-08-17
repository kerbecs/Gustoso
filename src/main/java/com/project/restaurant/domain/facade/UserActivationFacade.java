package com.project.restaurant.domain.facade;

public interface UserActivationFacade {
    boolean activateUserByToken(String token);

}
