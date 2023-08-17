package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserRegisterMapper {
    public abstract UserDto userRegisterDtoToUserDto(UserRegisterDto userRegisterDto);

    private final UserDescriptionMapper userDescriptionMapper = Mappers.getMapper(UserDescriptionMapper.class);

    public User userRegisterDtoToUser(UserRegisterDto userRegisterDto) {
        return User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .userDescription(
                        userDescriptionMapper.userDescriptionDtoToUserDescription(userRegisterDto.getUserDescriptionDto())
                )
                .build();
    }

    public UserRegisterDto userToUserRegisterDto(User user) {
        return UserRegisterDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .repeatPassword(user.getPassword())
                .role(user.getRoleList()
                        .stream()
                        .reduce((role1, role2) -> role1.getRole().ordinal() < role2.getRole().ordinal() ? role2 : role1)
                        .get().toString())
                .userDescriptionDto(userDescriptionMapper.userDescriptionToUserDescriptionDto(user.getUserDescription()))
                .build();

    }
}
