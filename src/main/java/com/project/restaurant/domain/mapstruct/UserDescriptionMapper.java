package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.UserDescriptionDto;
import com.project.restaurant.domain.entity.UserDescription;
import org.mapstruct.Mapper;

@Mapper
public interface UserDescriptionMapper {
    UserDescriptionDto userDescriptionToUserDescriptionDto(UserDescription userDescription);
    UserDescription userDescriptionDtoToUserDescription(UserDescriptionDto userDescriptionDto);

}
