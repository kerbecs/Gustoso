package com.project.restaurant.domain.dto;

import com.project.restaurant.adapters.validationGroup.UserModifyProfile;
import com.project.restaurant.adapters.validationGroup.UserRegister;
import com.project.restaurant.domain.annotation.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class UserDescriptionDto {
    private Integer id;

    @Pattern(regexp = "^([A-Za-z\']{2,25})$", groups = {UserRegister.class}, message = "{firstName.invalid.message}")
    private String firstName;

    @Pattern(regexp = "^([A-Za-z\']{2,25})$", groups = {UserRegister.class}, message = "{lastName.invalid.message}")
    private String lastName;

    @Email(groups = {UserRegister.class}, message = "{email.invalid.message}")
    @NotEmpty(groups = {UserRegister.class}, message = "{email.invalid.message}")
    @Unique(column = "email", table = "UserDescription", message = "{email.unique.message}", groups = {UserRegister.class})
    private String email;

    @Pattern(regexp = "^([A-Za-z\\-\\s\']{2,25})$", groups = {UserRegister.class}, message = "{city.invalid.message}")
    private String city;

    @Pattern(regexp = "^([A-Za-z0-9\\s\\\\\'\\-]{5,70})$", groups = {UserRegister.class}, message = "{address.invalid.message}")
    private String address;

    @Size(max = 100, groups = {UserModifyProfile.class}, message = "{userDescription.tooLong.message}")
    private String userDescription;

    @Pattern(regexp = "^([A-Za-z\\-_ \']{0,20})$", groups = {UserModifyProfile.class}, message = "{job.invalid.message}")
    private String job;

    private Integer orders;

}
