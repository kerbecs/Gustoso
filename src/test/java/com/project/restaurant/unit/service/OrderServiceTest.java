package com.project.restaurant.unit.service;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.adapters.serviceimpl.OrderServiceImpl;
import com.project.restaurant.domain.repository.OrderRepository;
import com.project.restaurant.domain.service.OrderService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.project.restaurant.util.Utility.ORDER_LIST;
import static com.project.restaurant.util.Utility.USER_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private final OrderRepository orderRepository = mock();
    private final OrderService orderService = new OrderServiceImpl(orderRepository);

    @Test
    void testGetOrderByUserId() {
        when(orderService.getOrderByUserId(USER_LIST.get(0).getId())).thenReturn(ORDER_LIST.subList(0, 2));

        assertThat(orderService.getOrderByUserId(USER_LIST.get(0).getId())).isEqualTo(ORDER_LIST.subList(0, 2));
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(ORDER_LIST);

        assertThat(orderRepository.findAll()).isEqualTo(ORDER_LIST);
    }

    @Test
    void testGetOrderById() {
        when(orderRepository.findById(ORDER_LIST.get(0).getId())).thenReturn(Optional.ofNullable(ORDER_LIST.get(0)));

        assertThat(orderService.getOrderById(ORDER_LIST.get(0).getId())).isEqualTo((ORDER_LIST.get(0)));

    }

    @Test
    void testGetOrderByIdWhenInvalidId() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getOrderById(100)).isInstanceOf(NoSuchElementException.class);

    }

    @Test
    void testSave() {
        orderService.save(ORDER_LIST.get(0));

        verify(orderRepository).save(ORDER_LIST.get(0));
    }

}
