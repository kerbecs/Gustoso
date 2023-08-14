package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.OrderProduceDto;
import com.project.restaurant.domain.entity.OrderProduce;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProduceMapper {
    OrderProduceDto orderProduceToOrderProduceDto(OrderProduce orderProduce);
    OrderProduce orderProduceDtoToOrderProduce(OrderProduce orderProduce);
}
