package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.UserDescriptionDto;
import com.project.restaurant.domain.entity.UserDescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserDescriptionMapper {
    @Mapping(target = "userDescription", source = "description")
    UserDescriptionDto userDescriptionToUserDescriptionDto(UserDescription userDescription);

    @Mapping(target = "description", source = "userDescription")
    UserDescription userDescriptionDtoToUserDescription(UserDescriptionDto userDescriptionDto);

}
