package com.project.restaurant.domain.dto;

import com.project.restaurant.adapters.validationGroup.UserRegister;
import com.project.restaurant.domain.annotation.Unique;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRegisterDto {
    private Integer id;
    @Pattern(regexp = "^([a-zA-Z_0-9.-]{5,68})$", groups = {UserRegister.class}, message = "{username.invalid.message}")
    @Unique(table = "User", column = "username", message = "{username.unique.message}", groups = {UserRegister.class})
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,60}$", groups = {UserRegister.class}, message = "{password.invalid.message}")
    private String password;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,60}$", groups = {UserRegister.class}, message = "{password.invalid.message}")
    private String repeatPassword;


    @NotNull
    private Integer isActive;

    private String role;

    @Valid
    private UserDescriptionDto userDescriptionDto;

}
