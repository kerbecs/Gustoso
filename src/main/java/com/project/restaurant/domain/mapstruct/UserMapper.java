package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.entity.Role;
import com.project.restaurant.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    private final UserDescriptionMapper userDescriptionMapper = Mappers.getMapper(UserDescriptionMapper.class);

    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .userDescription(userDescriptionMapper.userDescriptionDtoToUserDescription(userDto.getUserDescriptionDto()))
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .isActive(userDto.getIsActive())
                .id(userDto.getId())
                .build();
    }

    public UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .userDescriptionDto(userDescriptionMapper.userDescriptionToUserDescriptionDto(user.getUserDescription()))
                .role(getHigherRole(user.getRoleList()))
                .username(user.getUsername())
                .password(user.getPassword())
                .isActive(user.getIsActive())
                .id(user.getId())
                .build();
    }

    public String getHigherRole(List<Role> roleList) {
        return roleList
                .stream()
                .map(Role::getRole)
                .sorted(Comparator.comparingInt(Enum::ordinal))
                .findFirst()
                .get()
                .toString();
    }
}
