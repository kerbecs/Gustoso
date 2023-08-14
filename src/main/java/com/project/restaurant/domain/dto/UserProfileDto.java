package com.project.restaurant.domain.dto;

import com.project.restaurant.adapters.validationGroup.UserModifyProfile;
import com.project.restaurant.adapters.validationGroup.UserRegister;
import com.project.restaurant.domain.annotation.ModifyPassword;
import com.project.restaurant.domain.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserProfileDto {
    private Integer id;
    private String username;


    @Pattern(regexp = "^([A-Za-z\']{2,25})$", message = "{firstName.invalid.message}")
    private String firstName;

    @Pattern(regexp = "^([A-Za-z\']{2,25})$", message = "{lastName.invalid.message}")
    private String lastName;

    @ModifyPassword(message = "{password.invalid.message}")
    private String password;

    private String email;

    @Pattern(regexp = "^([A-Za-z\\-\\s\']{2,25})$", message = "{city.invalid.message}")
    private String city;

    @Pattern(regexp = "^([A-Za-z0-9\\s\\\\\'\\-]{5,70})$", message = "{address.invalid.message}")
    private String address;

    @Size(min = 0, max = 100, groups = {UserModifyProfile.class}, message = "{userDescription.tooLong.message}")
    private String userDescription;

    @Pattern(regexp = "^([A-Za-z\\-_ \']{0,20})$", message = "{job.invalid.message}")
    private String job;

    private Integer orders;

    private Integer isActive;

    private Integer descriptionId;

    private List<Role> roleList;
}
