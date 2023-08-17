package com.project.restaurant.unit.controller;

import com.project.restaurant.adapters.controller.rest.OrderController;
import com.project.restaurant.domain.facade.OrderFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.project.restaurant.util.Utility.ORDER_DTO_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    private final OrderFacade orderFacade = Mockito.mock();
    private final OrderController orderController = new OrderController(orderFacade);

    @Test
    void testGetAllOrders() {
        when(orderFacade.getAllOrders()).thenReturn(ORDER_DTO_LIST);

        assertThat(orderController.getAllOrders())
                .isEqualTo(ResponseEntity.ok(ORDER_DTO_LIST));
    }

    @Test
    void testGetOrderById() {
        when(orderFacade.getOrderById(1)).thenReturn(ORDER_DTO_LIST.get(0));

        assertThat(orderController.getOrderById(1))
                .isEqualTo(ResponseEntity.ok(ORDER_DTO_LIST.get(0)));
    }
    @Test
    void testGetOrdersByUserId(){
        when(orderFacade.getOrdersByUserId(1)).thenReturn(ORDER_DTO_LIST.subList(0,2));

        assertThat(orderController.getOrdersByUserId(1)).isEqualTo(ResponseEntity.ok(ORDER_DTO_LIST.subList(0,2)));
    }
}
