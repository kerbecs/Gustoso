package com.project.restaurant.adapters.facadeimpl;

import com.project.restaurant.domain.dto.OrderDto;
import com.project.restaurant.domain.facade.OrderFacade;
import com.project.restaurant.domain.mapstruct.OrderMapper;
import com.project.restaurant.domain.service.OrderService;
import com.project.restaurant.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(int id) {
        return orderMapper.orderToOrderDto(orderService.getOrderById(id));
    }

    @Override
    public List<OrderDto> getOrdersByUserId(int id) {
        userService.getUserById(id);

        return orderService.getOrderByUserId(id)
                .stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
    }
}
