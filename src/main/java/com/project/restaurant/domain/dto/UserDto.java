package com.project.restaurant.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.restaurant.adapters.validationGroup.UserRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserDto {
    private Integer id;

    private String username;

    @Pattern(regexp = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$", groups = {UserRegister.class}, message = "{password.invalid.message}")
    @Size(max = 60, groups = {UserRegister.class}, message = "{password.invalid.message}")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private String password;

    private Integer isActive;

    private String role;

    @Valid
    private UserDescriptionDto userDescriptionDto;

}
