package com.project.restaurant.unit.facade;

import com.project.restaurant.adapters.facadeimpl.OrderFacadeImpl;
import com.project.restaurant.domain.facade.OrderFacade;
import com.project.restaurant.domain.mapstruct.OrderMapper;
import com.project.restaurant.domain.service.OrderService;
import com.project.restaurant.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.project.restaurant.util.Utility.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderFacadeTest {
    private final OrderService orderService = mock();
    private final UserService userService = mock();
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    private final OrderFacade orderFacade = new OrderFacadeImpl(orderService,userService,orderMapper);

    @Test
    void testGetAllOrders(){
        when(orderService.getAllOrders()).thenReturn(ORDER_LIST);

        assertThat(orderFacade.getAllOrders()).isEqualTo(ORDER_DTO_LIST);
    }

    @Test
    void testGetOrderById(){
        when(orderService.getOrderById(1)).thenReturn(ORDER_LIST.get(0));

        assertThat(orderFacade.getOrderById(1)).isEqualTo(ORDER_DTO_LIST.get(0));
    }
    @Test
    void testGetOrderByUserId(){
        when(userService.getUserById(1)).thenReturn(USER_LIST.get(0));
        when(orderService.getOrderByUserId(1)).thenReturn(ORDER_LIST.subList(0,2));


        assertThat(orderFacade.getOrdersByUserId(1)).isEqualTo(ORDER_DTO_LIST.subList(0,2));
    }


}
