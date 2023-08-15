package com.project.restaurant.adapters.controller;

import com.project.restaurant.domain.facade.UserActivationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserActivateController {
    private final UserActivationFacade userActivationFacade;

    @GetMapping("/confirm-email")
    @PreAuthorize(value = "permitAll()")
    public String confirmUserEmail(@RequestParam("id") String token){
        if(!userActivationFacade.activateUserByToken(token))
            return "invalidToken";
        return "registered";
    }
}
