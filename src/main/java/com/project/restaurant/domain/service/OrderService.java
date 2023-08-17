package com.project.restaurant.domain.service;

import com.project.restaurant.domain.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderByUserId(int id);

    List<Order> getAllOrders();

    Order getOrderById(int id);

    void save(Order order);
}
