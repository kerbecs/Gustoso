package com.project.restaurant.unit.controller;

import com.project.restaurant.adapters.controller.UserActivateController;
import com.project.restaurant.domain.facade.UserActivationFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserActivateControllerTest {
    private final UserActivationFacade userActivationFacade = mock();
    private final UserActivateController controller = new UserActivateController(userActivationFacade);

    @Test
    void testConfirmUserEmailWhenInvalidToken() {
        String token = "test";

        when(userActivationFacade.activateUserByToken(token)).thenReturn(false);

        assertThat(controller.confirmUserEmail(token)).isEqualTo("invalidToken");

        verify(userActivationFacade).activateUserByToken(token);
    }

    @Test
    void testConfirmUserEmailWhenValidToken() {
        String token = "test";

        when(userActivationFacade.activateUserByToken(token)).thenReturn(true);

        assertThat(controller.confirmUserEmail(token)).isEqualTo("registered");

        verify(userActivationFacade).activateUserByToken(token);
    }
}
