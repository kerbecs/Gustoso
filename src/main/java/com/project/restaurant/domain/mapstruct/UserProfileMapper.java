package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.UserProfileDto;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.entity.UserDescription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    default User userProfileDtoToUser(UserProfileDto userProfileDto) {
        UserDescription userDescription = UserDescription.builder()
                .description(userProfileDto.getUserDescription())
                .email(userProfileDto.getEmail())
                .lastName(userProfileDto.getLastName())
                .firstName(userProfileDto.getFirstName())
                .address(userProfileDto.getAddress())
                .job(userProfileDto.getJob())
                .city(userProfileDto.getCity())
                .id(userProfileDto.getDescriptionId())
                .build();

        return User.builder()
                .userDescription(userDescription)
                .id(userProfileDto.getId())
                .isActive(userProfileDto.getIsActive())
                .username(userProfileDto.getUsername())
                .password(userProfileDto.getPassword())
                .roleList(userProfileDto.getRoleList())
                .build();
    }

    default UserProfileDto userToUserProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .firstName(user.getUserDescription().getFirstName())
                .lastName(user.getUserDescription().getLastName())
                .email(user.getUserDescription().getEmail())
                .city(user.getUserDescription().getCity())
                .job(user.getUserDescription().getJob())
                .userDescription(user.getUserDescription().getDescription())
                .address(user.getUserDescription().getAddress())
                .orders(user.getUserDescription().getOrders())
                .isActive(user.getIsActive())
                .descriptionId(user.getUserDescription().getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roleList(user.getRoleList())
                .build();
    }
}
