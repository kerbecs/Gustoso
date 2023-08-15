package com.project.restaurant.adapters.controller;

import com.project.restaurant.adapters.validationGroup.UserModifyProfile;
import com.project.restaurant.adapters.validationGroup.UserRegister;
import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserOrderDto;
import com.project.restaurant.domain.dto.UserProfileDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.facade.MvcFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@SessionAttributes(value = {"order","user"})
public class MvcController {
    private final ApplicationContext applicationContext;
    private final MvcFacade mvcFacade;

    @GetMapping
    @PreAuthorize(value = "permitAll()")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/loginPage")
    @PreAuthorize(value = "permitAll()")
    public String getLoginPage(Principal principal) {
        return principal == null ? "login" : "redirect:/";
    }

    @GetMapping("/registerPage")
    @PreAuthorize(value = "permitAll()")
    public String getRegisterPage(Model model) {
        model.addAttribute("userRegister", new UserRegisterDto());

        return "register";
    }

    @PostMapping("/register")
    @PreAuthorize(value = "permitAll()")
    public String registerNewUser(@ModelAttribute("userRegister") @Validated(value = {UserRegister.class}) UserRegisterDto userRegisterDto, BindingResult bindingResult) {
        mvcFacade.checkPasswords(userRegisterDto.getPassword(),userRegisterDto.getRepeatPassword(),bindingResult);

        if (bindingResult.hasErrors())
            return "register";

        mvcFacade.saveUser(userRegisterDto);

        return "activated";
    }

    @GetMapping("/orderPage")
    @PreAuthorize(value = "permitAll()")
    public String getOrderPage(Model model) {
        model.addAttribute("produces", mvcFacade.getAllProduces());
        model.addAttribute("order", applicationContext.getBean("userOrderDto"));
        return "order";
    }

    @PostMapping("/makeOrder")
    @PreAuthorize(value = "isAuthenticated()")
    public String makeOrder(Principal principal, @ModelAttribute("order") UserOrderDto userOrderDto) {

        mvcFacade.makeOrder(principal.getName(), userOrderDto);

        return "thx";
    }

    @GetMapping("/profile")
    @PreAuthorize(value = "isAuthenticated()")
    public String getProfilePage(Principal principal, Model model) {
        String username = principal.getName();

        model.addAttribute("user", mvcFacade.getUserProfileByUsername(username));

        return "profile";
    }
    @PostMapping("/updateUser")
    @PreAuthorize(value = "isAuthenticated()")
    public String updateProfile(@ModelAttribute("user") @Valid UserProfileDto userProfileDto, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
             mvcFacade.saveUser(userProfileDto);

        return "profile";
    }
    @GetMapping("/aboutUs")
    @PreAuthorize(value = "permitAll()")
    public String aboutUsPage(){
        return "about";
    }
    @GetMapping("/error")
    @PreAuthorize(value = "permitAll()")
    public String errorPage(){
        return "error";
    }
    @GetMapping("/registered")
    @PreAuthorize(value = "permitAll()")
    public String registeredPage(){
        return "registered";
    }
    @GetMapping("/noPermission")
    @PreAuthorize(value = "permitAll()")
    public String noPermissionPage(){
        return "noPermission";
    }

}
