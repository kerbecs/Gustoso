package com.project.restaurant.domain.facade;

import com.project.restaurant.domain.dto.OrderDto;

import java.util.List;

public interface OrderFacade {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(int id);
    List<OrderDto> getOrdersByUserId(int id);
}
