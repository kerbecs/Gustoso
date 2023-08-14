package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.entity.UserDescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Mapper(componentModel = "spring")
public abstract class UserRegisterMapper {
   public  abstract UserDto userRegisterDtoToUserRegister(UserRegisterDto userRegisterDto);
   private final UserDescriptionMapper userDescriptionMapper = Mappers.getMapper(UserDescriptionMapper.class);

    public  User userRegisterDtoToUser(UserRegisterDto userRegisterDto){
        return User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .userDescription(
                        userDescriptionMapper.userDescriptionDtoToUserDescription(userRegisterDto.getUserDescriptionDto())
                )
                .build();
    }
}
