package com.project.restaurant.unit.facade;

import com.project.restaurant.adapters.facadeimpl.UserFacadeImpl;
import com.project.restaurant.domain.facade.UserFacade;
import com.project.restaurant.domain.mapstruct.UserMapper;
import com.project.restaurant.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.project.restaurant.util.Utility.USER_DTO_LIST;
import static com.project.restaurant.util.Utility.USER_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTest {
    private final UserService userService = mock();
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserFacade userFacade = new UserFacadeImpl(userService, userMapper);

    @Test
    void testGetAllUsers(){
        when(userService.getAllUsers()).thenReturn(USER_LIST);

        assertThat(userFacade.getAllUsers()).isEqualTo(USER_DTO_LIST);
    }
    @Test
    void testGetUserByUsername(){
        when(userService.getUserByUsername(USER_LIST.get(0).getUsername())).thenReturn(USER_LIST.get(0));

        assertThat(userFacade.getUserByUsername(USER_LIST.get(0).getUsername())).isEqualTo(USER_DTO_LIST.get(0));
    }
}
