package com.project.restaurant.unit.facade;

import com.project.restaurant.adapters.facadeimpl.UserActivationFacadeImpl;
import com.project.restaurant.domain.entity.ConfirmationToken;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.facade.UserActivationFacade;
import com.project.restaurant.domain.service.ConfirmationTokenService;
import com.project.restaurant.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.restaurant.util.Utility.ROLE_LIST;
import static com.project.restaurant.util.Utility.USER_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserActivationFacadeTest {
    private final UserService userService = mock();
    private final ConfirmationTokenService tokenService = mock();
    private final UserActivationFacade facade = new UserActivationFacadeImpl(userService, tokenService);

    @Test
    void testActivateUserByTokenWhenConfirmationTokenIsNull() {
        when(tokenService.findTokenByConfirmationToken("test")).thenReturn(null);

        assertThat(facade.activateUserByToken("test")).isFalse();
    }

    @Test
    void testActivateUserByTokenWhenConfirmationTokenIsNotNullButUserIsActive() {
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .user(USER_LIST.get(0))
                .confirmationToken("test token")
                .expireDate(LocalDateTime.now())
                .build();

        when(tokenService.findTokenByConfirmationToken("test")).thenReturn(confirmationToken);

        assertThat(facade.activateUserByToken("test")).isFalse();
    }

    @Test
    void testActivateUserByTokenWhenConfirmationTokenIsNotNullButTokenIsExpired() {
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .user(USER_LIST.get(0))
                .confirmationToken("test token")
                .expireDate(LocalDateTime.now().minusHours(2))
                .build();

        when(tokenService.findTokenByConfirmationToken("test")).thenReturn(confirmationToken);

        assertThat(facade.activateUserByToken("test")).isFalse();
    }

    @Test
    void testActivateUserByTokenWhenConfirmationTokenIsNotNullAndValid() {
        User user = User.builder()
                .username("testUsername2")
                .password("test123")
                .isActive(0)
                .roleList(List.of(ROLE_LIST.get(0)))
                .build();

        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .user(user)
                .confirmationToken("test token")
                .expireDate(LocalDateTime.now().plusMinutes(20))
                .build();

        when(tokenService.findTokenByConfirmationToken("test")).thenReturn(confirmationToken);

        assertThat(facade.activateUserByToken("test")).isTrue();

        verify(userService).activateUser(user.getUsername());

    }
}
