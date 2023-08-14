package com.project.restaurant.domain.mapstruct;

import com.project.restaurant.domain.dto.OrderDto;
import com.project.restaurant.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);

    @Mapping(target = "id", ignore = true)
    Order orderDtoToOrder(OrderDto orderDto);
}
