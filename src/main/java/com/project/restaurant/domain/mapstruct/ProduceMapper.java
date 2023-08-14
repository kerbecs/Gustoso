package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.ProduceDto;
import com.project.restaurant.domain.entity.Produce;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduceMapper {
    Produce produceDtoToProduce(ProduceDto produceDto);

    ProduceDto produceToProduceDto(Produce produce);
}
