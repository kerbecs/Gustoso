package com.project.restaurant.unit.controller;

import com.project.restaurant.adapters.controller.rest.UserController;
import com.project.restaurant.domain.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.project.restaurant.util.Utility.USER_DTO_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private static final UserFacade userFacade = Mockito.mock();
    private static final UserController userController = new UserController(userFacade);
    @Test
    void testGetAllUsers(){
        when(userFacade.getAllUsers()).thenReturn(USER_DTO_LIST);

        assertThat(userController.getAllUsers()).isEqualTo(ResponseEntity.ok(USER_DTO_LIST));
    }
    @Test
    void testGetUserByUsername(){
        when(userFacade.getUserByUsername("testUsername1")).thenReturn(USER_DTO_LIST.get(0));

        assertThat(userController.getUserByUsername("testUsername1")).isEqualTo(ResponseEntity.ok(USER_DTO_LIST.get(0)));
    }
}
