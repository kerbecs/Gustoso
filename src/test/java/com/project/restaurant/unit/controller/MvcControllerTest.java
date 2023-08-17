package com.project.restaurant.unit.controller;

import com.project.restaurant.adapters.controller.MvcController;
import com.project.restaurant.domain.dto.UserOrderDto;
import com.project.restaurant.domain.dto.UserProfileDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.facade.MvcFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MvcControllerTest {
    private final ApplicationContext applicationContext = mock();
    private final MvcFacade mvcFacade = mock();
    private final MvcController mvcController = new MvcController(applicationContext, mvcFacade);

    @Test
    void testGetIndexPage() {
        assertThat(mvcController.getIndexPage()).isEqualTo("index");
    }

    @Test
    void testGetLoginPage() {
        Principal principal = mock();

        assertAll(
                () -> assertThat(mvcController.getLoginPage(null)).isEqualTo("login"),
                () -> assertThat(mvcController.getLoginPage(principal)).isEqualTo("redirect:/")
        );
    }

    @Test
    void testGetRegisterPage() {
        assertThat(mvcController.getRegisterPage(mock(Model.class))).isEqualTo("register");
    }

    @Test
    void testRegisterNewUserWithNoError() {
        UserRegisterDto userRegisterDto = mock();
        BindingResult bindingResult = mock();

        when(bindingResult.hasErrors()).thenReturn(false);
        assertThat(mvcController.registerNewUser(userRegisterDto, bindingResult)).isEqualTo("activated");
    }

    @Test
    void testRegisterNewUserWithError() {
        UserRegisterDto userRegisterDto = mock();
        BindingResult bindingResult = mock();

        when(bindingResult.hasErrors()).thenReturn(true);
        when(userRegisterDto.getPassword()).thenReturn("password");
        when(userRegisterDto.getRepeatPassword()).thenReturn("password");

        assertThat(mvcController.registerNewUser(userRegisterDto, bindingResult)).isEqualTo("register");
        verify(mvcFacade).checkPasswords(userRegisterDto.getPassword(), userRegisterDto.getRepeatPassword(), bindingResult);

    }

    @Test
    void testGetOrderPage() {
        Model model = mock();

        assertThat(mvcController.getOrderPage(model)).isEqualTo("order");
    }

    @Test
    void testMakeOrder() {
        Principal principal = mock();
        UserOrderDto userOrderDto = mock();

        when(principal.getName()).thenReturn("name");

        assertThat(mvcController.makeOrder(principal, userOrderDto)).isEqualTo("thx");
        verify(mvcFacade).makeOrder(principal.getName(), userOrderDto);
    }

    @Test
    void testGetProfilePage() {
        Principal principal = mock();
        Model model = mock();

        assertThat(mvcController.getProfilePage(principal, model)).isEqualTo("profile");
    }

    @Test
    void testUpdateProfileWhenNoError() {
        UserProfileDto userProfileDto = mock();
        BindingResult bindingResult = mock();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mvcFacade.saveUser(userProfileDto)).thenReturn(userProfileDto);

        assertThat(mvcController.updateProfile(userProfileDto, bindingResult)).isEqualTo("profile");
        verify(mvcFacade).saveUser(userProfileDto);

    }

    @Test
    void testUpdateProfileWhenThereIsError() {
        UserProfileDto userProfileDto = mock();
        BindingResult bindingResult = mock();

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThat(mvcController.updateProfile(userProfileDto, bindingResult)).isEqualTo("profile");
    }

    @Test
    void testGetAboutUsPage() {
        assertThat(mvcController.aboutUsPage()).isEqualTo("about");
    }

    @Test
    void testGetErrorPage() {
        assertThat(mvcController.errorPage()).isEqualTo("error");
    }

    @Test
    void testGetRegisteredPage() {
        assertThat(mvcController.registeredPage()).isEqualTo("registered");
    }

    @Test
    void testGetNoPermissionPage() {
        assertThat(mvcController.noPermissionPage()).isEqualTo("noPermission");
    }

}

