package com.project.restaurant.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.restaurant.adapters.validationGroup.UserRegister;
import com.project.restaurant.domain.annotation.Unique;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private Integer id;

    private String username;

    @Pattern(regexp = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$",groups = {UserRegister.class}, message = "{password.invalid.message}")
    @Size(min = 0, max = 60,groups = {UserRegister.class}, message = "{password.invalid.message}")
    @JsonIgnore
    private String password;

    private Integer isActive;

    private String role;

    @Valid
    private UserDescriptionDto userDescriptionDto;

}
